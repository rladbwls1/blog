package mini.spring.mvc.repository;

import mini.spring.mvc.bean.MemberDTO;

public interface MemberMapper {

	public int loginCheck(MemberDTO dto);
	public int getStatus(String id);
	
	public int confirmId(String id);
	
	public void signup(MemberDTO dto);
}
