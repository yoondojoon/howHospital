package kr.or.iei.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptData {
	private int memberNo;
	private String regReservation;
	private int hospitalNo;
	private String memberName;
	private String memberAddress;
	private int doctorNo;
	private String doctorName;
	private String hospitalName;
	private String costOne;
	private String memberPhone;
	private int reservationNo;
}
