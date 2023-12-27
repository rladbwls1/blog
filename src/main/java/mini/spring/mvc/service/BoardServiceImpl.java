package mini.spring.mvc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.BoardDTO;
import mini.spring.mvc.bean.CommendDTO;
import mini.spring.mvc.bean.FileDTO;
import mini.spring.mvc.repository.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void commendWrite(CommendDTO dto) {
		mapper.commendWrite(dto);
	}

	@Override
	public void deleteComm(int num) {
		mapper.deleteComm(num);
	}

	@Override
	public void write(BoardDTO dto) {
		mapper.write(dto);
	}

	@Override
	public void upload(ArrayList<MultipartFile> files, String path, FileDTO dto) {
		int db_boardnum = mapper.maxNum();
		for(int i = 0; i < files.size(); i++) {
			String uuid = UUID.randomUUID().toString();
			MultipartFile f = files.get(i);
			String filename = f.getOriginalFilename();
			if(!f.getOriginalFilename().equals("")) {
				String main_img = "N";
				if(i == 0) {
					main_img = "Y";
				} 
				System.out.println(main_img);
				dto.setMain_img(main_img); 
				String ext = filename.substring(filename.lastIndexOf("."));
				filename = "file_" + db_boardnum + "_" + uuid + ext; 
				File copy = new File(path + filename);
				dto.setFilename(filename);
				dto.setDb_boardnum(db_boardnum);
				mapper.upload(dto);
				String name = mapper.filename(db_boardnum);
				mapper.filenameUp(db_boardnum, name);
				try {
					f.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<String> imgPrint(int db_boardnum) {
		return mapper.imgPrint(db_boardnum);
	}

	@Override
	public int writeDel(int num, String pw, String path) {
		int result = 0;
		String passwd = mapper.pwCheck(num);
		List<String> filename = mapper.imgPrint(num);
		if(passwd.equals(pw)) {
			for(String f : filename) {
				imgDel(path, f);
			}
			mapper.deleteCom(num);
			mapper.imgDelete(num);
			result = mapper.writeDel(num, pw);
		}
		return result;
	}
	
	@Override
	public void updateComm(int num, String content) {
		mapper.updateComm(num, content);
	}

	public int deleteI(String [] filename, String path, int isFile) {
		for(String f : filename) {
			imgDel(path, f);
			mapper.imgD(f);
			isFile--;
		}
		return isFile;
	}

	public void imgDel(String path, String filename) {
		File file = new File(path + filename.trim());
		if(file.isFile()) {
			file.delete();
		}
	}
	
	public int passwd(String pw, int num) {
		int result = 0;
		String passwd = mapper.pwCheck(num);
		if(passwd.equals(pw)) {
			result = 1;
		}
		return result;
	}

	@Override
	public int updateWrite(String pw, BoardDTO dto, int num) {
		int result = 0;
		result = mapper.updateWrite(dto);
		return result;
	}

	@Override
	public List<CommendDTO> commendList(int boardnum, Model model) {
		model.addAttribute("count", mapper.commendCount(boardnum));
		return mapper.commendList(boardnum);
	}

	@Override
	public BoardDTO contentList(int num) {
		mapper.countNum(num);
		return mapper.contentList(num);
	}

	@Override
	public List<BoardDTO> boardList() {
		return mapper.boardList();
	}
}
