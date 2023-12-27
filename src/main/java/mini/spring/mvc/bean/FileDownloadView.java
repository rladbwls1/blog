package mini.spring.mvc.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("downView")		//==bean태그, <bean id="downView" class=""...
public class FileDownloadView extends AbstractView{

	public FileDownloadView() {
		//setContentType("application/download;charset=UTF-8");
	}
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file=(File)model.get("downFile");
		//response.setCharacterEncoding(getContentType());	//인코딩 타입 설정
		response.setContentLength((int)file.length());		//다운로드양을, 응답할 때 전해진 파일 크기 만큼.
		
		//한글 이름이 있으면 안됨
		//URLEncoder로...해결...//url 주소상에 들어가는 한글을 처리하기 위함 
		String fileName=URLEncoder.encode(file.getName(),"UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out=response.getOutputStream();
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		}catch(Exception e) { e.printStackTrace();	
		}finally {
			if(fis!=null)try {fis.close();}catch(Exception e2){}
		}
		out.flush();
	}

}
