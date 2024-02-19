package kr.or.iei.etc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etc")
public class EtcController {
	@GetMapping("/pharmacy")
	public String pharmacy() {
		return "etc/searchPharmacy";
	}
}
