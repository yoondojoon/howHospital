package kr.or.iei.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Community {
	private int boardNo;
	private int memberNo;
	private String boardTitle;
	private String boardContent;
	private String writeDate;
	private String boardWriter;
	private int readCount;
}
