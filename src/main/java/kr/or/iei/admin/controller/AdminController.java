package kr.or.iei.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@GetMapping(value="/adminMain")
	public String adminMain() {
		return "/admin/adminMain";
	}
}
