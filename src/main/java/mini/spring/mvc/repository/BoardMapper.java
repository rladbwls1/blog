package mini.spring.mvc.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mini.spring.mvc.bean.BoardDTO;
import mini.spring.mvc.bean.CommendDTO;
import mini.spring.mvc.bean.FileDTO;

public interface BoardMapper {
	public List<BoardDTO> boardList();
	public BoardDTO contentList(int num);
	public void write(BoardDTO dto);
	public int countNum(int num);
	public void upload(FileDTO dto);
	public int maxNum();
	public List<String> imgPrint(int db_boardnum);
	public int writeDel(@Param("num") int num, 
			             @Param("pw") String pw);
	public String pwCheck(int num);
	public void imgDelete(int db_boardnum);
	public int updateWrite(BoardDTO dto);
	public void commendWrite(CommendDTO dto);
	public int commendCount(int boardnum);
	public List<CommendDTO> commendList(int boardnum);
	public void deleteComm(int num);
	public void imgD(String filename);
	public void deleteCom(int db_boardnum);
	public void updateComm(@Param("num") int num, 
			             @Param("content") String content);
	public String filename(int db_boardnum);
	public void filenameUp(@Param("num")int num, @Param("filename")String filename);
}
