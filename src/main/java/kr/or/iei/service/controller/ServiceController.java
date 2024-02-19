package kr.or.iei.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/service")
public class ServiceController {

	@GetMapping(value="/searchHospital")
	public String searchHospital() {
		return "/service/searchHospital";
	}
	
}
