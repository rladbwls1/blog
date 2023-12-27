package mini.spring.mvc.bean;

import java.sql.Date;

import lombok.Data;
@Data
public class NoticeReplyDTO {
	private int num;
	private int noticenum;
	private String id;
	private String content;
	private Date reg;
	
}
