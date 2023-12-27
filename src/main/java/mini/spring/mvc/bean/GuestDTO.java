package mini.spring.mvc.bean;

import java.util.Date;

import lombok.Data;

@Data
public class GuestDTO {
	private int num;
	private String writer;
	private String content;
	private Date reg_date;
	private int ref;
	private int re_step;
}
