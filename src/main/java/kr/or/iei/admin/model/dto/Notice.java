package kr.or.iei.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notice {
	private int noticeNo;
	private int memberNo;
	private String noticeTitle;
	private String noticeContent;
	private int readCount;
	private String ReqDate;
	private String memberName;
}
