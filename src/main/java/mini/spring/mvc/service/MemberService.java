package mini.spring.mvc.service;

import mini.spring.mvc.bean.MemberDTO;

public interface MemberService {
	public int loginCheck(MemberDTO dto);
	public int getStatus(String id);
	
	public int confirmId(String id);
	
	public void signup(MemberDTO dto);
}
