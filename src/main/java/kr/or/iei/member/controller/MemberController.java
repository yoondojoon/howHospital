package kr.or.iei.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	
	
	@PostMapping(value="/signUpFrm")
	public String signUpFrm() {
		
		
		return "member/signUpFrm";
		
	}
	
}
