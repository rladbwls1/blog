package mini.spring.mvc.bean;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String passwd;
	private String name;
	private int status;
}
