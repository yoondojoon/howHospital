package kr.or.iei;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry
				.addResourceHandler("/**")
				.addResourceLocations("classpath:/templates/","classpath:/static/");
			registry
				.addResourceHandler("/doctor/**")
				.addResourceLocations("file:///C:/Temp/hospital/doctor/");
			registry
				.addResourceHandler("/hospital/image/**")
				.addResourceLocations("file:///C:/Temp/hospital/hospital/");
			registry
			.addResourceHandler("/auth/**")
			.addResourceLocations("file:///C:/Temp/hospital/auth/");
			registry
				.addResourceHandler("/reservation/**")
				.addResourceLocations("file:///C:/Temp/hospital/reservation/");
			registry
				.addResourceHandler("/review/**")
				.addResourceLocations("file:///C:/Temp/hospital/review/");

			registry
				.addResourceHandler("/community/**")
				.addResourceLocations("file:///C:/Temp/hospital/community/");

			registry
				.addResourceHandler("/community/editor/**")
				.addResourceLocations("file:///C:/Temp/hospital/community/editor/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//로그인
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/admin/**","/etc/receipt","/hospital/**")
		.excludePathPatterns("/admin/blockMsg","/hospital/hospitalMsg","/hospital/image/*","/admin/loginMsg","/admin/faqList","/admin/noticeList*");
		
		//차단자 
		registry.addInterceptor(new BlockInterceptor())
		.addPathPatterns("/admin/**","/etc/receipt","/hospital/**")
		.excludePathPatterns("/admin/blockMsg","/hospital/hospitalMsg","/hospital/image/*","/admin/loginMsg","/admin/blockMsg","/admin/faqList","/admin/noticeList*");
		
		//병원
		registry.addInterceptor(new HospitalInterceptor())
		.addPathPatterns("/hospital/**")
		.excludePathPatterns("/hospital/hospitalMsg","/hospital/image/*", "/hospital/myHospitalReviewList");
		
		//관리자
		registry.addInterceptor(new AdminInterceptor())
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/admin/blockMsg","/admin/manageMember","/admin/loginMsg","/admin/blockMsg","/admin/faqList","/admin/noticeList*");
		
	}
}
