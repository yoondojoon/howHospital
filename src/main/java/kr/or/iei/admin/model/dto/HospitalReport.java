package kr.or.iei.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalReport {
	private int reportNo;
	private int reservationNo;
	private String reportTitle;
	private String reportContent;
	private int reportStatus;
	private int memberNo;
	private String hospitalName;
}