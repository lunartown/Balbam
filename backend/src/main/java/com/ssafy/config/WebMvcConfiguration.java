package com.ssafy.config;

import java.util.Arrays;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer{
	
//	private final List<String> patterns = Arrays.asList("/article/*", "/admin/**", "/user/list");
	
//	private ConfirmInterceptor confirmInterceptor;

//	public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
//		System.out.println("WebMvcConfiguration 생성자 호출!!!");
//		this.confirmInterceptor = confirmInterceptor;
//	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//		.allowedOrigins("http://localhost:8080)", "http://70.12.103.80")
//		.allowedMethods("GET", "POST", ....)
		.allowedMethods("GET","POST", "UPDATE","DELETE")
//		.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(),
//		HttpMethod.OPTIONS.name(),HttpMethod.PATCH.name(),HttpMethod.HEAD.name())
		.maxAge(1800);
			
	}

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index");
////		registry.addRedirectViewController("/", "/list");
//	}

	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
}
