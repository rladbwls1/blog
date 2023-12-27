package mini.spring.mvc.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import mini.spring.mvc.bean.GuestDTO;
import mini.spring.mvc.repository.GuestMapper;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	private GuestMapper mapper;

	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public void list(Model model) {
		int count = mapper.count();
		List<GuestDTO> list = Collections.EMPTY_LIST;
		list = mapper.list();
		model.addAttribute("list", list);
		model.addAttribute("count", count);
	}
	

	@Override
	public String writer(String id) {
		return mapper.writer(id);
	}

	@Override
	public void write(GuestDTO dto) {
		int num = mapper.maxNum()+1;
		if(dto.getNum() !=0 ) { //�۹�ȣ�� 0�� �ƴ� �� update�� �ϰ� ���� ref_step +1 �ؼ� �亯�� �� �� �� �ֵ���
			dto.setRe_step(1);
		}else {
			dto.setRef(num);
		}
		mapper.writeInsert(dto);
	}

	@Override
	public int delete(int ref) {
		return mapper.delete(ref);
	}

	
}
