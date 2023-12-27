package mini.spring.mvc.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CommendDTO {
	private int num;
	private int boardnum;
	private String id;
	private String content;
	private Date reg;
}
