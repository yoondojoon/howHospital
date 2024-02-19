package kr.or.iei.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hospital")
public class HospitalController {
	
	@GetMapping(value="/myHospitalMain")
	public String myHospitalMain() {
		return "hospital/myHospitalMain";
	}
	
	@GetMapping(value="/myHospitalFrm")
	public String myHospitalFrm() {
		return "hospital/myHospitalFrm";
	}
	
	
	
}



