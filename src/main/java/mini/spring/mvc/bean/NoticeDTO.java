package mini.spring.mvc.bean;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeDTO {
	private int num;
	private String subject;
	private String content;
	private int isfile;
	private Date reg;

}
