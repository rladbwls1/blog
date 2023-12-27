package mini.spring.mvc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.ImgBoardDTO;
import mini.spring.mvc.bean.ImgBoardFileDTO;
import mini.spring.mvc.repository.ImgBoardMapper;

@Service
public class ImgBoardServiceImpl implements ImgBoardService{
	@Autowired
	private ImgBoardMapper mapper;

	@Override
	public int fileUpload(ArrayList<MultipartFile> files, String path, ImgBoardDTO dto) {
		int result = 0;
		int imgboarNum = mapper.maxNum();
		for(int i = 0; i < files.size(); i++) {
			MultipartFile f = files.get(i);
			String fileName = f.getOriginalFilename();
			if(!fileName.equals("")) {
				String ext = fileName.substring(fileName.lastIndexOf("."));
				fileName = "file_"+imgboarNum+"_"+i+ext;
				if(i==0) {
					dto.setImg_main(fileName);
				}
				File copy = new File(path+fileName);
				result = mapper.fileInsert(imgboarNum, fileName);
				try {
					f.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public ImgBoardDTO readContent(int num) {
		mapper.updateCountUp(num);
		return mapper.readNum(num);
	}

	@Override
	public void create(ImgBoardDTO dto) {
		int number = mapper.maxNum()+1;
		mapper.writeInsert(dto);
	}

	@Override
	public List<String> fileNum(int imgboardnum) {
		return mapper.fileNum(imgboardnum);
	}

	@Override
	public ImgBoardDTO update(int num) {
		return mapper.readNum(num);
	}

	@Override
	public int updateNum(ImgBoardDTO dto) {
		return mapper.updateNum(dto);
	}

	@Override
	public int deleteNum(int num, String passwd, String filePath) {
		String dbpw = readPasswd(num);
		int result = 0;
		if (dbpw.equals(passwd)) {
			deleteFile(num, filePath);
			result = mapper.deleteNum(num);
		}
		return result;
	}
	
	public void deleteFile(int num , String filePath) {
		List<String> file = mapper.fileNameSelect(num);
		for(String fileName : file) {
			File copy = new File(filePath+fileName);
			copy.delete();
		}
	}

	@Override
	public String readPasswd(int num) {
		return mapper.readPasswd(num);
	}

	@Override
	public List<String> fileNameSelect(int imgboardnum) {
		return mapper.fileNameSelect(imgboardnum);
	}

	@Override
	public List<ImgBoardDTO> list() {
		return mapper.list();
	}

	@Override
	public List<ImgBoardFileDTO> fileList(int num) {
		return mapper.fileList(num);
	}

	@Override
	public List<ImgBoardFileDTO> selectefile(int imgboardnum) {
		return mapper.selectefile(imgboardnum);
	}

	@Override
	public String getThumb() {
		int num=mapper.maxNum();
		return mapper.getThumb(num);
	}

	@Override
	public void updateImgMain(String img_main) {
		int num=mapper.maxNum();
		mapper.updateImgMain(img_main, num);		
	}
	
	
}
