package mini.spring.mvc.repository;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import mini.spring.mvc.bean.QADTO;
import mini.spring.mvc.bean.QAFileDTO;

public interface QAMapper {
	public List<QADTO> list();
	public QADTO readNum(int num);
	public int maxNum();
	public void writeUpdate(QADTO dto);
	public void writeInsert(QADTO dto);
	public void updateCountUp(int num);
	public int updateNum(QADTO dto);
	public String readPasswd(int num);
	public int deleteNum(int num);
	
	public List<String> filenameimport(int qanum);
	public List<String> filenameimport2(int qanum);
	public List<QAFileDTO> fileList(int qanum);
	public int fileInsert (@Param("qanum") int qanum,
			@Param("filename") String filename,
			@Param("orgname") String orgname);
	public int deleteqanum(int qanum);
}
