package mini.spring.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.ImgBoardDTO;
import mini.spring.mvc.bean.ImgBoardFileDTO;
import mini.spring.mvc.service.ImgBoardService;


@Controller
@RequestMapping("/imgboard/*")
public class ImgBoardController {
	private static final Logger logger = LoggerFactory.getLogger(ImgBoardController.class);
	
	@Autowired
	private ImgBoardService service;
	
	@RequestMapping("list")
	public String list(Model model) {
	    List<ImgBoardDTO> list = service.list();
	    model.addAttribute("list", list);
	    return "imgboard/list";
	}
	

	@RequestMapping("writeForm")
	public String imgwrite(Model model,@RequestParam(value="num",defaultValue="0") int num, HttpSession session) {
		String id = (String) session.getAttribute("memId");
		model.addAttribute("id", id);
		model.addAttribute("num",num);
		return "/imgboard/writeForm";
	}
	
	@RequestMapping("writePro")
	public String imgwriterPro(@RequestParam("files") MultipartFile[] files, ImgBoardDTO dto, HttpServletRequest request) {
	    int result = 0;
	    int isfile = 0;
	    String mainimg = null;
	    for (MultipartFile f : files) {
	        if (!f.getOriginalFilename().equals("")) {
	            isfile++;
	        }
	    }
	    dto.setIsfile(isfile);
	    service.create(dto);
	    if (isfile > 0) {
	    	String filePath = request.getServletContext().getRealPath("/resources/file/board/");
	    	result = service.fileUpload(new ArrayList<>(Arrays.asList(files)), filePath, dto);
	    	mainimg=service.getThumb();
	    	service.updateImgMain(mainimg);
	    }
	    
	    return "redirect:/imgboard/list";
	}

	
	@RequestMapping("content")
	public String content(int num, Model model, HttpSession session) {
		String id = (String) session.getAttribute("memId");
		ImgBoardDTO dto = service.readContent(num);
		List<ImgBoardFileDTO> fileList = null;
		if(dto.getIsfile() > 0) {
			fileList = service.fileList(num);
		}
		model.addAttribute("id",id);
		model.addAttribute("fileList",fileList);
		model.addAttribute("dto", dto);
		return "imgboard/content";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, int num) {
		ImgBoardDTO dto = service.update(num);
		model.addAttribute("dto", dto);
		return "imgboard/updateForm";
	}
	
	@RequestMapping("updatePro")
	public String updatePro(Model model, int num, ImgBoardDTO dto ) {
		int check = service.updateNum(dto);
		model.addAttribute("check", check);
		return "redirect:/imgboard/list";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(Model model, int num) {
		model.addAttribute("num", num);	
		return "imgboard/deleteForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(Model model, String passwd, int num, ImgBoardDTO dto, HttpServletRequest request ) {
		String filePath = request.getServletContext().getRealPath("/resources/file/board/");
		int check = service.deleteNum(num, passwd, filePath);
		model.addAttribute("check", check);
		return "imgboard/deletePro";
	}

}
