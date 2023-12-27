package mini.spring.mvc.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private String pw;
	private Date reg;
	private int count;
	private int isfile;
	private String filename;
	private FileDTO file;
}
