package mini.spring.mvc;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.service.MemberService;
import mini.spring.mvc.service.QAService;
import mini.spring.mvc.bean.QAFileDTO;
import mini.spring.mvc.QAController;
import mini.spring.mvc.bean.QADTO;

@Controller
@RequestMapping("/qa/*")
public class QAController {
	
	private static final Logger logger = LoggerFactory.getLogger(QAController.class);
	
	@Autowired
	private QAService service;
	@Autowired
	private MemberService mservice;

	@RequestMapping("list")
	public String list(Model model) {
		List<QADTO> list = service.list();
		model.addAttribute("article",list);
		return "qa/list";
	}
	
	@RequestMapping("writeForm")
	public String writeForm(HttpSession session, Model model,
			@RequestParam(value="num",defaultValue = "0")int num,
			@RequestParam(value="ref",defaultValue = "1")int ref,
			@RequestParam(value="re_step",defaultValue = "0")int re_step,
			@RequestParam(value="re_level",defaultValue = "0")int re_level ,String title){
		if(session.getAttribute("memId")!=null) {
			String id=(String)session.getAttribute("memId");
			model.addAttribute("id", id);
			model.addAttribute("num",num);
			model.addAttribute("ref",ref);
			model.addAttribute("re_step",re_step);
			model.addAttribute("re_level",re_level);
			model.addAttribute("re_level",re_level);
		}
		if(title!=null) {
			model.addAttribute("title",title);
		}
		return "qa/writeForm";
	}
	
	@RequestMapping("writePro")
	public String writePro(ArrayList<MultipartFile> files, QADTO dto, HttpServletRequest request) {
		int isfile = 0, result=0;
		for(MultipartFile f : files) {
			if(!f.getOriginalFilename().equals("")){
				isfile++;
				String orgName = f.getOriginalFilename();
			}
		}
		dto.setIsfile(isfile);
		service.create(dto);
		if(isfile > 0) {
			String filePath = request.getServletContext().getRealPath("/resources/file/qa/");			
			result = service.fileUpload(files,filePath);
		}
		return "redirect:/qa/list";
	}
	
	@RequestMapping("content")
	public String content(Model model, int num,HttpSession session) {
		QADTO article = service.readContent(num);
		int status=0;
		if(session.getAttribute("memId")!=null) {
			String id=(String)session.getAttribute("memId");
			status=mservice.getStatus(id);
			model.addAttribute("id",id);
		}
		List<QAFileDTO> fileList = null;
		if(article.getIsfile() > 0) {
			fileList = service.fileList(num);
		}
		model.addAttribute("fileList",fileList);
		model.addAttribute("list",service.filenameimport(num));
		model.addAttribute("status",status);
		model.addAttribute("article",article);
		return "qa/content";
	}
	@RequestMapping("updateForm")
	   public String upadateForm(Model model, int num) {
	      QADTO article = service.update(num);
	      List<QAFileDTO> fileList = null;
	      if(article.getIsfile() > 0) {
	         fileList = service.fileList(num);
	      }
	      model.addAttribute("fileList",fileList);
	      model.addAttribute("article",article);
	  return "qa/updateForm";
	   }
	@RequestMapping("updatePro")
	public String updatePro(Model model, QADTO dto) {
		int check = service.updateNum(dto);
		model.addAttribute("check",check);
		return "qa/updatePro";
	}
	@RequestMapping("deleteForm")
	public String deleteForm(Model model, int num) {
		
		model.addAttribute("num",num);
		return "qa/deleteForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(Model model, int num , String passwd, HttpServletRequest request) {
		String filePath = request.getServletContext().getRealPath("/resources/file/qa/");
		int check = service.deleteNum(num,passwd,filePath);
		model.addAttribute("check",check);
		return "qa/deletePro";
	}
	
}
