package kr.or.iei.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class H_Reservation {
	private int reservationNo;
	private int reservationStatus;
	private String regReservation;
	private String reservationTime;
	private String memberName;
	private String doctorName;
}
