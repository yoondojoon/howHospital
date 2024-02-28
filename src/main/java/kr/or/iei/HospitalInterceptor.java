package kr.or.iei;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.iei.member.model.dto.Member;


public class HospitalInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		System.out.println("병원 인터셉터 : " + member.getMemberType());
		//로그인이 되어있다 가정하는 코드 : 로그인안되어있으면 nullpointException 뜸
		if(member.getMemberType() != 2){
			response.sendRedirect("/admin/hospitalMsg");
			return false;			
		}else {
			return true;
		}
	}
}
