package kr.or.iei.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.reservation.model.service.ReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	@ResponseBody
	@GetMapping("/updateReservationStatus")
	public int updateReservationStatus(int reservationNo) {
		int result = reservationService.updateReservationStatus(reservationNo);
		if(result>0) {
			return 1;
		}else {
			return 0;
		}
	}
}
