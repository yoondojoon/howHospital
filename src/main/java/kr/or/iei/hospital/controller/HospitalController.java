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

import com.google.gson.JsonObject;

import kr.or.iei.hospital.model.service.DoctorService;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.test.dto.ReservationDetail;
import kr.or.iei.hospital.model.service.HospitalService;
import kr.or.iei.test.service.ReservationDetailService;
import kr.or.iei.test.service.ReservationService;

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
	
	@GetMapping(value="/myHospitalFrm")
	public String myHospitalFrm() {
		return "hospital/myHospitalFrm";
	}
	
	@GetMapping(value="/businessAuth")
	public String businessAuth() {
		return "hospital/businessAuth";
	}
	
	
	@GetMapping("/myHospitalReservation")
	public String myHospitalReservation(Model model) {
		//병원 예약 조회해오기
		List list = reservationService.selectReservation();
		model.addAttribute("reservation", list);
		//예약한 환자 조회
		Member m = memberService.selectMember();
		if(m != null) {
			model.addAttribute("member", m);
		}
		//예약 상세 조회(의사 번호)
		String doctorName = reservationDetailService.selectDoctor();
		System.out.println(doctorName);
		model.addAttribute("doctorName", doctorName);
		return "hospital/myHospitalReservationList";
	}
	
	
	
	//BizNo API
	@ResponseBody
	@PostMapping(value="/bizApi")
	public String bizApi(String representativeNo) {
	    String url = "https://api.odcloud.kr/api/nts-businessman/v1/status";
		String serviceKey = "LYwyMLoRsjjpbQnMxPjkRjfcBDZSyBT8kX+5eUe+GzOstpDBQxojDsd9wg/K0fDcgo/12RKWXUttts6z8AA5YA==";
	       // JSON 요청 데이터 생성
        JsonObject requestData = new JsonObject();
        requestData.addProperty("b_no", representativeNo);

        try {
            // API 요청
            String result = Jsoup.connect(url)
                .data("serviceKey", serviceKey)
                .data("b_no", representativeNo)
                .ignoreContentType(true)
                .post()
                .text();
            
            // API 응답을 JSON 객체로 파싱
            JsonObject responseJson = JsonParser.parseString(result).getAsJsonObject();
            
            // API 응답 반환
            return ResponseEntity.ok().body(responseJson.toString());
        } catch (Exception e) {
            // API 요청 실패 시 에러 응답 반환
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API 요청 실패");
        }
    }

}
	
	
	
}



