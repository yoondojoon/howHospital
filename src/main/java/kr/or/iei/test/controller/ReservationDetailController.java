package kr.or.iei.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.test.service.ReservationDetailService;

@Controller
@RequestMapping("/ReservationDetail")
public class ReservationDetailController {
	@Autowired
	private ReservationDetailService reservationDetailService;
}
