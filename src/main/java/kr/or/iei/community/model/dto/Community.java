package kr.or.iei.community.model.dto;

import java.util.List;

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
	private List<CommunityFile> fileList;
	
	public String getBoardContentBr() {
		return boardContent.replaceAll("\r\n", "<br>");
	}
}
