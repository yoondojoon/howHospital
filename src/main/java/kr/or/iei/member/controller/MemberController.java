package kr.or.iei.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	
	
	@GetMapping(value="/signUpFrm")
	public String signUpFrm() {
		
		
		return "member/signUpFrm";
		
	}
	
	
	@GetMapping(value="/signInFrm")
	public String signInFrm() {
		
		
		return "member/signInFrm";
		
	}
	
	
	
	
	
}
