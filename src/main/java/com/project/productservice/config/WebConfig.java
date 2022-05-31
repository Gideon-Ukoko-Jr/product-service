package com.project.productservice.config;

import com.project.productservice.controllers.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new Interceptor())
				.excludePathPatterns("/public")
				.excludePathPatterns("/webjars/**")
				.excludePathPatterns("/configuration/**")
				.excludePathPatterns("/swagger-ui.html")
				.excludePathPatterns("/swagger-ui.html#/**")
				.excludePathPatterns("/swagger-resources/**")
				.excludePathPatterns("/v2/api-docs")
				.excludePathPatterns("/csrf")
				.excludePathPatterns("/error")
				.excludePathPatterns("/customer/add");
	}

}
