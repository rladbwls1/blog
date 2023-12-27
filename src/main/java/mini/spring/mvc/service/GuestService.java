package mini.spring.mvc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import mini.spring.mvc.bean.GuestDTO;


public interface GuestService {
	public int count();
	public void list(Model model);
	
	public String writer(String id);
	
	public void write(GuestDTO dto); //writeInsert
	
	public int delete(int ref);
	
}
