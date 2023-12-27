package mini.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.spring.mvc.bean.MemberDTO;
import mini.spring.mvc.repository.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper mapper;

	@Override
	public int loginCheck(MemberDTO dto) {
		return mapper.loginCheck(dto);
	}

	@Override
	public int getStatus(String id) {
		return mapper.getStatus(id);
	}

	@Override
	public int confirmId(String id) {
		return mapper.confirmId(id);
	}

	@Override
	public void signup(MemberDTO dto) {
		mapper.signup(dto);
		
	}
	

}
