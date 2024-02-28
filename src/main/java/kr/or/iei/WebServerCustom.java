package kr.or.iei;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustom implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		//404에러 처리 boot.webserver
		//->Http의 상태가 not_found이면 error/notFound로 이동
		ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error/notFound");
		
		//500에러 처리
		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/serverError");
	
		factory.addErrorPages(error404,error500);
	}
	//서버 자체가 발생시키는 에러를 처리하는 페이지
	
	
	
}
