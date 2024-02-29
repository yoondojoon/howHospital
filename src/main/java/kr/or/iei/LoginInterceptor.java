package kr.or.iei;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.iei.member.model.dto.Member;

public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//true면 실행 false면 실행안함
		//Session 생성
		HttpSession session = request.getSession();
		//회원정보 꺼냄
		Member member = (Member)session.getAttribute("member");
		//로그인이 되어있을때
		if(member != null) {
			return true;
		//로그인이 되어있지 않을때
		}else {
			response.sendRedirect("/admin/loginMsg");
			//return false를 선택하면 controller로 안간다
			return false;
		}
	}
}
