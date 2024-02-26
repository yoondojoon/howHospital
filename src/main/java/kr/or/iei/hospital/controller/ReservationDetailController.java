package kr.or.iei.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationDetailList;
import kr.or.iei.reservation.model.service.ReservationDetailService;
import kr.or.iei.reservation.model.service.ReservationService;

@Controller
@RequestMapping("/ReservationDetail")
public class ReservationDetailController {
	@Autowired
	private ReservationDetailService reservationDetailService;
	@Autowired
	private ReservationService reservationService;
	
	@ResponseBody
	@GetMapping("/updateReservation")
	public int updateReservation(H_Reservation hr) {
		System.out.println(hr);
		int result = reservationService.updateReservationDetail(hr);
		if(result > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
