package mini.spring.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.NoticeDTO;
import mini.spring.mvc.bean.NoticeReplyDTO;

public interface NoticeService {
	public void insert(NoticeDTO dto);
	public ArrayList<NoticeDTO> getlist();
	public NoticeDTO getNotice(int num);
	public void update(NoticeDTO dto);
	public void delete(int num,String path);
	public void fileUpload(ArrayList<MultipartFile> files, String path);
	public void fileUpload2(ArrayList<MultipartFile> files, String path,int num);
	public ArrayList<String> getFilenames(int num);
	public void insertReply(NoticeReplyDTO dto);
	public ArrayList<NoticeReplyDTO> replys(int num);
	public void deleteReply(int num);
	public ArrayList<String> getImgFiles(int num);
	public int updateProFile(String deletefiles, String path,int isfile);
	public HashMap<Integer,Integer> countReply();

}
