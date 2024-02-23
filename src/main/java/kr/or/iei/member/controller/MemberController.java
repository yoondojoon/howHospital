package kr.or.iei.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.or.iei.EmailSender;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;
import lombok.Getter;

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
	public String signIn(String memberEmail, String memberPassword, HttpSession session, Model model) {
		
		
		Member member = memberService.signIn(memberEmail,memberPassword);
		
		if(member == null) {
			
			return "member/signInFail";
		}else {
			
			if(member.getMemberStatus()==2) {
				
				
				session.setAttribute("member", member);
				
				
				return "hospital/businessAuth";
				
			}
			
			
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
	
	//회원탈퇴 화면
	@GetMapping(value="/memberDelete")
	public String delete(@SessionAttribute(required = false) Member member) {
		
		
		
		return "member/memberDelete";
	}
	
	
	
	//회원탈퇴 확인
	@GetMapping(value="/confirmDelete")
	public String confirmDelete() {
		
		return "member/confirmDelete";
		
		
	}
	
	@ResponseBody
	@PostMapping(value="/emailChk")
	public int emailChk(@RequestParam("memberEmail") String memberEmail ) {
		
		int cnt = memberService.checkEmail(memberEmail);
		
		return cnt;
		
		
	}
	
	
	
	//서비스 이용 약관
	@GetMapping(value="/agreeService")
	public String agreeService() {
		
		
		return "/member/agreeService";
		
	}
	
	
	//위치 이용 약관
	@GetMapping(value="/agreeLocation")
	public String agreeLocation() {
		
		return "/member/agreeLocation";
		
	}
	
	//나이 이용 약관
	@GetMapping(value="/agreeAge")
	public String agreeAge() {
		
		return "/member/agreeAge";
	}
	
	
	//회원탈퇴 실패
	@GetMapping(value="/failDeleteMember")
	public String failDeleteMember() {
		
		return "/member/failDeleteMember";
		
	}
	
	
	//회탈
	@PostMapping(value="/delete")
	public String confirmDelete(@SessionAttribute(required = false) Member member, Member m) {
		
		String memberEmail = member.getMemberEmail();
		
		
		int cnt = memberService.confirmDelete(memberEmail,m);
		
		
		if(cnt == 0) {
			
				return "/member/failDeleteMember";
			
			
		}else {
			
			
			return "redirect:/";
			
		}
		
		
		
		
		
	}
	
	
	//이메일 찾기
	@GetMapping(value="/findEmail")
	public String findEmail() {
		
		return "/member/findEmail";
		
	}
	
	//비밀번호 찾기
	@GetMapping(value="/findPassword")
	public String findPassword() {
		
		
		
		return "/member/findPassword";
	}
	
	
	
	
	
	
}







