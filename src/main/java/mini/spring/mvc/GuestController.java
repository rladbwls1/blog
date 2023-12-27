package mini.spring.mvc;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.spring.mvc.bean.GuestDTO;
import mini.spring.mvc.service.GuestService;
import mini.spring.mvc.service.MemberService;

@Controller
@RequestMapping("/guest/*")
public class GuestController {

	@Autowired
	private GuestService service;
	
	@Autowired
	private MemberService mservice;
	
	@RequestMapping("list")
	public String list(Model model, HttpSession session) {
		int status = 0;
		String  name = null;
		if(session.getAttribute("memId") != null) { //로그인이 된 상태에서만 status가 있기 때문에 조건문으로 처리해줘야함
			String id = (String)session.getAttribute("memId");
			status = mservice.getStatus(id);
			name = service.writer(id);
		}
		model.addAttribute("status", status);
		model.addAttribute("name", name);
		service.list(model); 
		return "guest/list";
	}
	@RequestMapping("writeForm")
	public String writeForm(Model model, HttpSession session,
			@RequestParam(value="num", defaultValue="0") int num,
			@RequestParam(value="ref", defaultValue="0") int ref,
			@RequestParam(value="re_step", defaultValue="0") int re_step) {
		//String id = (String)session.getAttribute("memId");
		String name = (String)session.getAttribute("memName");
		model.addAttribute("writer", name);
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		return "guest/writeForm";
	}
	@RequestMapping("writePro")
	public String writePro(GuestDTO dto) {
		service.write(dto);
		return "redirect:/guest/list";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(Model model, int ref) {
		model.addAttribute("ref", ref);
		return "guest/deleteForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(Model model, int ref) {
		model.addAttribute("check", service.delete(ref));
		return "guest/deletePro";
	}
	

}
