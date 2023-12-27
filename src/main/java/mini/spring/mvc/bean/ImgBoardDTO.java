package mini.spring.mvc.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class ImgBoardDTO {
	private int num;
	private String writer;
	private String subject;
	private String content;
	private String passwd;
	private Date reg_date;
	private int readcount;
	private int isfile;
	private String img_main;
}