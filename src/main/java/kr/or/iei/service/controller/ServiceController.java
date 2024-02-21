package kr.or.iei.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping(value="/searchHospital")
	public String searchHospital(String keyword, Model model) {
		System.out.println(keyword);
		List hospitalList = hospitalService.searchHospital(keyword);
		model.addAttribute("hospitalList",hospitalList);
		if(hospitalList != null) {
			model.addAttribute("listSize",hospitalList.size());
			model.addAttribute("keyword",keyword);
		}
		return "/service/searchHospitalMain";
	}
	
}
