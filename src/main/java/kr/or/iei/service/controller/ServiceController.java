package kr.or.iei.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.hospital.model.service.HospitalService;

@Controller
@RequestMapping(value="/service")
public class ServiceController {
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping(value="/searchHospitalMain")
	public String searchHospitalMain() {
		return "/service/searchHospitalMain";
	}
	
	@GetMapping(value="/hospitalDetail")
	public String hospitalDetail() {
		return "/service/hospitalDetail";
	}
	
	@ResponseBody
	@GetMapping(value="/searchHospital")
	public List searchHospital(String keyword) {
		List list = hospitalService.searchHospital(keyword);
		return list;
	}
	
}
