package mini.spring.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.BoardDTO;
import mini.spring.mvc.bean.CommendDTO;
import mini.spring.mvc.bean.FileDTO;

public interface BoardService {
	public List<BoardDTO> boardList();
	public BoardDTO contentList(int num);
	public void write(BoardDTO dto);
	public void upload(ArrayList<MultipartFile> files, String path, FileDTO dto);
	public List<String> imgPrint(int db_boardnum);
	public int writeDel(int num, String pw, String path);
	public int updateWrite(String pw, BoardDTO dto, int num);
	public void commendWrite(CommendDTO dto);
	public List<CommendDTO> commendList(int boardnum, Model model);
	public void deleteComm(int num);
	public void updateComm(int num, String content);
}
