package kr.or.iei.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.hospital.model.dto.Hospital;
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
	
	@ResponseBody
	@GetMapping(value="/searchHospital")
	public List searchHospital(String keyword) {
		List list = hospitalService.searchHospital(keyword);
		return list;
	}
	
	@GetMapping(value="/hospitalDetail")
	public String hospitalDetail(int hospitalNo, Model model) {
		Hospital h = hospitalService.searchHospitalDetail(hospitalNo);
		model.addAttribute("h", h);
		return "/service/hospitalDetail";
	}
}
