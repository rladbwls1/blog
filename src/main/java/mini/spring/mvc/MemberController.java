package mini.spring.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.spring.mvc.bean.GuestDTO;
import mini.spring.mvc.bean.MemberDTO;
import mini.spring.mvc.service.GuestService;
import mini.spring.mvc.service.MemberService;

@Controller
@RequestMapping("/m/*")
public class MemberController {
	@Autowired
	private MemberService service;
	@Autowired
	private GuestService gservice;
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	@RequestMapping("loginPro")
	public String hello(MemberDTO dto, Model model) {
		int check=service.loginCheck(dto);
		model.addAttribute("check",check);
		dto.setName(gservice.writer(dto.getId()));
		model.addAttribute("dto",dto);
		model.addAttribute("status",service.getStatus(dto.getId()));
		return "member/loginPro";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session,String id) {
		session.invalidate();
		return "redirect:/m/main";
	}
	
	@RequestMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	@RequestMapping("confirmId")
	public String confirmid(@RequestParam("id") String id, Model model) {
		int result = service.confirmId(id);
		if(result == 0) {
			model.addAttribute("check", 0);
            model.addAttribute("id", id);
		}else {
			model.addAttribute("check", 1);
            model.addAttribute("id", id);
		}
		return "member/confirmId";
	}
	
	@RequestMapping("signupPro")
	public String signupPro(MemberDTO dto) {
		service.signup(dto);
		return "redirect:/m/loginForm";
	}
}
