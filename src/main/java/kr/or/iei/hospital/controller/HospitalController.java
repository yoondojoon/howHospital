package kr.or.iei.hospital.controller;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import jakarta.servlet.http.HttpSession;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.hospital.model.service.DoctorService;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.service.ReservationDetailService;
import kr.or.iei.reservation.model.service.ReservationService;
import kr.or.iei.hospital.model.service.HospitalService;

@Controller
@RequestMapping(value="/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private ReservationDetailService reservationDetailService;
	
	@GetMapping(value="/myHospitalMain")
	public String myHospitalMain() {
		return "hospital/myHospitalMain";
	}
	
	@PostMapping(value="/myHospitalFrm")
	public String myHospitalFrm(BusinessAuth ba, HttpSession session) {
		Member member = (Member)session.getAttribute("member");

		//1.사업자정보 insert
//		int result = hospitalService.insertBusinessAuth(ba, member.getMemberNo());
		
		//2. 성공하면 병원정보 폼
		
		return "hospital/myHospitalFrm";
	}
	
	@GetMapping(value="/businessAuth")
	public String businessAuth(Model model, HttpSession session) {
		
		return "hospital/businessAuth";
	}
	
	
	@GetMapping("/myHospitalReservation")
	public String myHospitalReservation(Model model) {
		//병원 예약 조회해오기
		List list = reservationService.selectReservation();
		System.out.println(list);
		model.addAttribute("reservation", list);
		return "hospital/myHospitalReservationList";
	}

	@ResponseBody
	@GetMapping("/changeReservationType")
	public int changeReservationType(int selectValue, int reservationNo) {
		//병원 예약 업데이트
		int result = reservationService.updateReservation(selectValue, reservationNo);
		if(result > 0) {
			return 1;
		}else {
			return 0;
		}
	}

	
}



