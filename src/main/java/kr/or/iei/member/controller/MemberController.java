package kr.or.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	
	
	
	//회원가입 페이지
	@GetMapping(value="/signUpFrm")
	public String signUpFrm() {
		
		
		return "member/signUpFrm";
		
	}
	
	//로그인 페이지
	@GetMapping(value="/signInFrm")
	public String signInFrm() {
		
		
		return "member/signInFrm";
		
	}
	
	//로그인 실패 페이지
	@GetMapping(value="/signInFail")
	public String signInFail() {
		
		return "member/signInFail";
	}
	
	//로그인 	
	@PostMapping(value="/signIn")
	public String signIn(String memberEmail, String memberPassword, HttpSession session) {
		
		
		Member member = memberService.signIn(memberEmail,memberPassword);
		
		if(member == null) {
			
			return "member/signInFail";
		}else {
			
			session.setAttribute("member", member);
			
			return "redirect:/";
			
		}
		
	}
	
	//이메일 인증
	@ResponseBody
	@PostMapping(value="/sendCode")
	public String sendCode(String memberEmail) {
		
		String authCode = emailSender.sendCode(memberEmail);
		return authCode;
		
	}
		
	//회원가입 성공 페이지
	@GetMapping(value="/successSignUp")
	public String successSignUp() {
		
		return "member/successSignUp";
		
		
	}
	
	//회원가입 실패 페이지
	@GetMapping(value="/failSignUp")
	public String failSignUp() {
		
		return "member/failSignUp";
		
		
	}
	
	
	//회원가입
	@PostMapping(value="/signUp")
	public String signUp(Member member, String postcode, String address, String detailAddress) {
		
		String memberAddress = postcode+" "+address+" "+detailAddress;
		
		System.out.println(memberAddress);
		member.setMemberAddress(memberAddress);
		
		
		int	result = memberService.signUp(member);
			
		
				
		
		if(result > 0 ) {
			
			return "member/successSignUp";
			
		}else {
			
			return "member/failSignUp";
		}
	}
	
	//로그아웃
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
				
		
		return "redirect:/";
	}
	
	//마이페이지
	@GetMapping(value="/myInfo")
	public String myPage() {
		
		return "member/myInfo";
		
		
	}
	
	//회원탈퇴(아직 안끝남)
	@GetMapping(value="/delete")
	public String delete(@SessionAttribute(required = false) Member member) {
		
		
		
		return "redirect:/";
	}
	
	
	
	
	
}







