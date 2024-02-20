package kr.or.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.or.iei.EmailSender;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailSender emailSender;
	
	
	
	
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
	
	@ResponseBody
	@PostMapping(value="/sendCode")
	public String sendCode(String memberEmail) {
		
		String authCode = emailSender.sendCode(memberEmail);
		return authCode;
		
	}
		
		
	@GetMapping(value="/successSignUp")
	public String successSignUp() {
		
		return "member/successSignUp";
		
		
	}
	
	@GetMapping(value="/failSignUp")
	public String failSignUp() {
		
		return "member/failSignUp";
		
		
	}
	
	@PostMapping(value="/signUp")
	public String signUp(Member member, String postcode, String address, String detailAddress) {
		
		String memberAddress = postcode+" "+address+" "+detailAddress;
		
		System.out.println(memberAddress);
		
		member.setMemberAddress(memberAddress);
		
				
		int result = memberService.signUp(member);
		
		if(result > 0 ) {
			
			return "member/successSignUp"; 
			
		}else {
			
			return "member/failSignUp";
		}
	}
	
	
	
}
