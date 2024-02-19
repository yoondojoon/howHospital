package kr.or.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	public MemberService memberService;
	
	
	@GetMapping(value="/signUpFrm")
	public String signUpFrm() {
		
		
		return "member/signUpFrm";
		
	}
	
	
	@GetMapping(value="/signInFrm")
	public String signInFrm() {
		
		
		return "member/signInFrm";
		
	}
	
	
	@GetMapping(value="/signInFail")
	public String signInFail() {
		
		return "member/signInFail";
	}
	
	@PostMapping(value="/signIn")
	public String signIn(String memberEmail, String memberPassword, HttpSession session) {
		
		
		Member member = memberService.signIn(memberEmail,memberPassword);
		
		if(member == null) {
			
			return "member/signInFail";
		}
		
		return "redirect:/";
		
	}
	
	
	
	
	
}
