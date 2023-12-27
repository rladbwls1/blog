package mini.spring.mvc.service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.QADTO;
import mini.spring.mvc.bean.QAFileDTO;
import mini.spring.mvc.repository.QAMapper;

@Service
public class QAServiceImpl implements QAService{
	
	@Autowired
	private QAMapper mapper;
	

	@Override
	public List<QADTO> list() {
		return mapper.list();
	}

	@Override
	public void create(QADTO dto) {
		int number = mapper.maxNum()+1;
		if(dto.getNum() != 0) {
			mapper.writeUpdate(dto);
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
		}else {
			dto.setRef(number);
		}
		mapper.writeInsert(dto);
	}

	@Override
	public QADTO readContent(int num) {
		mapper.updateCountUp(num);
		return mapper.readNum(num);
	}
	
	@Override
	public String readPasswd(int num) {
		return mapper.readPasswd(num);
	}

	@Override
	public int updateNum(QADTO dto) {
		String dbpw = readPasswd(dto.getNum());
		int result = 0;
		if(dbpw.equals(dto.getPasswd())) {
			result = mapper.updateNum(dto);
		}
		return result;
	}

	@Override
	public QADTO update(int num) {
		return mapper.readNum(num);
	}
	
	@Override
	public int deleteNum(int num, String passwd,String filePath) {
		String dbpw = readPasswd(num);
		int result = 0;
		if(dbpw.equals(passwd)) {
			if(mapper.filenameimport2(num).size()>0) {
				deleteFile(num,filePath);
				mapper.deleteqanum(num);
			}
			result = mapper.deleteNum(num);
		}
		return result;
	}

	@Override
	public int fileUpload(ArrayList<MultipartFile> files, String path) {
	    int result = 0;
	    int qanum = mapper.maxNum();

	    for (int i = 0; i < files.size(); i++) {
	        MultipartFile f = files.get(i);
	        String filename = f.getOriginalFilename();
	        String orgname=filename;
	        if (!filename.equals("")) {
	            String ext = filename.substring(filename.lastIndexOf("."));
	            filename = "file_" + qanum + "_" + i + ext;
	            File copy = new File(path + filename);
	            // 파일 정보와 게시글 정보를 함께 DB에 저장
	            result = mapper.fileInsert(qanum, filename, orgname);
	            try {
	                f.transferTo(copy);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return result;
	}
	
	public void deleteFile(int num, String filePath) {
		List<String> files=mapper.filenameimport2(num);
		for(String filename:files) {
			File copy = new File(filePath+filename);
			System.out.println(filePath);
			System.out.println(filename);
			copy.delete();
		}
	}

	@Override
	public List<String> filenameimport(int qanum) {
		return mapper.filenameimport(qanum);
	}

	@Override
	public List<QAFileDTO> fileList(int qanum) {
		return mapper.fileList(qanum);
	}

	@Override
	public List<String> filenameimport2(int qanum) {
		return mapper.filenameimport2(qanum);
	}
	

}
