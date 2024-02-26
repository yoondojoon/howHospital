package kr.or.iei.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
	private int reservationNo;
	private int hospitalNo;
	private int memberNo;
	private int reservationStatus;
	private String regReservation;
	private int reservationType;
	private String reservationTime;
	private String hospitalName;
	private String resTimeDate;
	private String resTimeDay;
	private String resTimeTime;
	private String memberName;
	private String childName;
}
