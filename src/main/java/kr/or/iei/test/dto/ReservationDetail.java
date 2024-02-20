package kr.or.iei.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDetail {

	private int reservationDetailNo;
	private int reservationNo;
	private int doctorNo;
	private int subjectNo;
	private String symptom;
}
