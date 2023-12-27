package mini.spring.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.BoardDTO;
import mini.spring.mvc.bean.CommendDTO;
import mini.spring.mvc.bean.FileDTO;
import mini.spring.mvc.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl service;
	
	@RequestMapping("list")
	public String list(Model model) {
		List<BoardDTO> boardList = service.boardList();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}

	@RequestMapping(value= "content", method = {RequestMethod.GET, RequestMethod.POST})
	public String content(Model model, int num, CommendDTO dto, @RequestParam(name = "msg", required = false) String msg) {
		BoardDTO boardList = service.contentList(num);
		List<String> img = service.imgPrint(num);
		List<CommendDTO> comm = service.commendList(num, model);
		model.addAttribute("boardList", boardList);
		model.addAttribute("num", num);
		System.out.println(msg);
		model.addAttribute("imgList", img);
		model.addAttribute("comm", comm);
		return "board/content";
	}

	@RequestMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteItem(@RequestParam("commNum") int commNum, @RequestParam("num") int num) {
	    service.deleteComm(commNum);
	    System.out.println(num);
	    return "redirect:/board/content?num=" + num;
	}

	@RequestMapping("deleteImg")
	public String deleteImg(String commNum) {
		System.out.println(commNum);
		return "board/deleteImg";
	}

	@RequestMapping("writePro")
	public String writePro(ArrayList<MultipartFile> upload, HttpServletRequest request, BoardDTO dto, FileDTO fdto) {
		int isfile = 0;
		for(MultipartFile file : upload) {
			if(!file.getOriginalFilename().equals("")) {
				isfile++;
			}
		}
		dto.setIsfile(isfile);
		service.write(dto);
		
		if(isfile > 0) {
			String path = request.getServletContext().getRealPath("/resources/db_image/board/");
			service.upload(upload, path, fdto);
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(Model model, int num, HttpServletRequest request, int check) {
		model.addAttribute("num", num);
		model.addAttribute("check", check);
		return "board/deleteForm";
	}
	
	@RequestMapping("commdel")
	public String commdel(int num, int comm) {
		service.deleteComm(comm);
		return "redirect:/board/content?num="+num;
	}
	@RequestMapping("commup")
	public String commup(int num, int comm, String content) {
		service.updateComm(comm, content);
		return "redirect:/board/content?num="+num;
	}
	
	@RequestMapping("deletePro")
	public String deletePro(BoardDTO dto, HttpServletRequest request, int num, String pw, Model model, int check) {
		String path = request.getServletContext().getRealPath("/resources/db_image/board/");
		int result = service.writeDel(num, pw, path);
		model.addAttribute("result", result);
		model.addAttribute("check", check);
		return "board/deletePro";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, int num) {
		BoardDTO boardList = service.contentList(num);
		List<String> img = service.imgPrint(num);
		model.addAttribute("num", num);
		model.addAttribute("boardList", boardList);
		model.addAttribute("img", img);
		return "board/updateForm";
	}
	
	@RequestMapping("updatePro")
	public String updatePro(int num, Model model, String pw) {
//		String [] F =ff.split(",");
//		System.out.println(F);
		int result = service.passwd(pw, num);
		model.addAttribute("result", result);
		model.addAttribute("num", num);
		return "board/updatePro";
	}

	@RequestMapping("upPro")
	public String upPro(HttpServletRequest request, FileDTO fdto, BoardDTO dto, int num, String pw, Model model, String ff, ArrayList<MultipartFile> upload) {
		int isfile = dto.getIsfile();
		System.out.println("1"+isfile);
		if(ff != null) {
			String [] F = ff.split(",");
			String path = request.getServletContext().getRealPath("/resources/db_image/board/");
			isfile = service.deleteI(F, path, isfile);
		}
		int result = service.updateWrite(pw, dto, num);
		for(MultipartFile file : upload) {
			if(!file.getOriginalFilename().equals("")) {
				isfile++;
			}
		}
		dto.setIsfile(isfile);
		System.out.println("¹¹¾ß");
		System.out.println("2"+isfile);
		if(isfile > 0) {
			System.out.println("¿Ö ¾Èµé¾î¿È");
			String path1 = request.getServletContext().getRealPath("/resources/db_image/board/");
			service.upload(upload, path1, fdto);
		}
		model.addAttribute("result", result);
		model.addAttribute("num", num);
		return "board/upPro";
	}
	
	@RequestMapping("comment")
	public String comment(CommendDTO dto, int boardnum) {
		service.commendWrite(dto);
		return "redirect:/board/content?num=" + boardnum;
	}
}
