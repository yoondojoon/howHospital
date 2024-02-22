package kr.or.iei.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberReport {
	private int reportNo; //신고번호
	private int boardNo; //신고 게시글 번호
	private int memberNo; //피신고자 번호
	private String reportTitle; //신고 제목
	private String reportContent; //신고내용
	private int reportStatus;	//신고처리상태
}
