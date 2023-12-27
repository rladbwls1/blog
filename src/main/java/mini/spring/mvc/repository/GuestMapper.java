package mini.spring.mvc.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import mini.spring.mvc.bean.GuestDTO;


public interface GuestMapper {
	public int count();
	public List<GuestDTO> list();
	
	public String writer(String id); 
	
	public int maxNum();
	public void writeInsert(GuestDTO dto);
	
	public int delete(int ref);
	
}
