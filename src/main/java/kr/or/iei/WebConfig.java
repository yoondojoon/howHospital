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
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		registry.addInterceptor(new BlockInterceptor())
		.addPathPatterns("/admin/**","/etc/**","/hospital/**")
		.excludePathPatterns("/admin/adminMsg","/admin/blockMsg");
		
		registry.addInterceptor(new AdminInterceptor())
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/admin/adminMsg","/admin/blockMsg");;
		
	}
}
