package mini.spring.mvc.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import mini.spring.mvc.bean.NoticeDTO;
import mini.spring.mvc.bean.NoticeReplyDTO;

public interface NoticeMapper {
	public void insert(NoticeDTO dto);
	public ArrayList<NoticeDTO> list();
	public NoticeDTO content(int num);
	public void update(NoticeDTO dto);
	public void delete(int num);
	public int maxNum();
	public void fileInsert(@Param("noticenum") int noticenum,
							@Param("filename") String filename,
							@Param("type") String type);
	public int isfile(int num);
	public void deleteFile(int num);
	public ArrayList<String> getFilenames(int num);
	public void insertReply(NoticeReplyDTO dto);
	public ArrayList<NoticeReplyDTO> replys(int num);
	public void deleteReply(int num);
	public void deleteReplys(int num);
	public ArrayList<String> imgFiles(int num);
	public void deleteFileByName(String filename);
	public int checkFile(int num);
	public String checkFileNum(int num);
}
