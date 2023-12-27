package mini.spring.mvc.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.QADTO;
import mini.spring.mvc.bean.QAFileDTO;

public interface QAService {
	
	public List<QADTO> list();
	public void create(QADTO dto);
	public QADTO readContent(int num);
	public int updateNum(QADTO dto);
	public QADTO update(int num);
	public String readPasswd(int num);
	public int deleteNum(int num, String passwd, String filePath);
	
	public int fileUpload(ArrayList<MultipartFile> files, String path);
	public List<String> filenameimport(int qanum);
	public List<String> filenameimport2(int qanum);
	public List<QAFileDTO> fileList(int qanum);
}
