package mini.spring.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.ImgBoardDTO;
import mini.spring.mvc.bean.ImgBoardFileDTO;

public interface ImgBoardService {
	
	public List<ImgBoardDTO> list();
	
	public List<ImgBoardFileDTO> selectefile(int imgboardnum);
	
	public int fileUpload(ArrayList<MultipartFile> files, String path, ImgBoardDTO dto);
	public String getThumb();
	public ImgBoardDTO readContent(int num);
	public void updateImgMain(String img_main);
	public void create(ImgBoardDTO dto);
	
	public List<String> fileNum(int imgboardnum);

	public ImgBoardDTO update(int num);
	public int updateNum(ImgBoardDTO dto);
	
	public int deleteNum(int num, String passwd, String filePath);
	public String readPasswd(int num);
	
	public List<String> fileNameSelect(int imgboardnum);
	public List<ImgBoardFileDTO> fileList(int num);
}
