package mini.spring.mvc.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mini.spring.mvc.bean.ImgBoardDTO;
import mini.spring.mvc.bean.ImgBoardFileDTO;


public interface ImgBoardMapper {
	public List<ImgBoardDTO> list();
	
	public int writeInsert(ImgBoardDTO dto);
	public int maxNum();
	public List<ImgBoardFileDTO> fileList(int imgboardnum);
	public int fileInsert(@Param("imgboardnum") int imgboardnum,
						  @Param("fileName") String fileName);
	public String getThumb(int num);
	public void updateImgMain(@Param("img_main") String img_main,
							@Param("num") int num);
	public List<ImgBoardFileDTO> selectefile(int imbaordnum);
	
	public void updateCountUp(int num);
	public ImgBoardDTO readNum(int num);
	public List<String> fileNum(int imgboardnum);
	public int updateNum(ImgBoardDTO dto);
	
	public int deleteNum(int num);
	public int deleteFile(int imgboardnum);
	public String readPasswd(int num);
	
	public List<String> deletefileNum(int imgboardnum);
	public List<String> fileNameSelect(int num);
	
	public void main_img(@Param("num") int num, @Param("img_main") String img_main);
	
}