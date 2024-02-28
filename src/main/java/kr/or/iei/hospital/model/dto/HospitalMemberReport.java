package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalMemberReport {
	private int repoNo;
	private int memberNo;
	private String memberName;
	private String reportReason;
	private int reviewNo;
	private int reviewReportStatus;
	private String repoDate;
}
