package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
	private int doctorNo;
	private int hospitalNo;
	private int subjectNo;
	private String doctorPicture;
	private String doctorName;
	private String doctorEducation;
	private String doctorExperience;
	private String subjectName;
}
