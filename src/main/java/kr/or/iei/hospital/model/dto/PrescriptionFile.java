package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrescriptionFile {
	private int prescriptionNo;
	private int reservationNo;
	private String prescriptionDate;
	private String prescriptionName;
	private String prescriptionPath;
}
