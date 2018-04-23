package com.github.herowzz.springfuse.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.herowzz.springfuse.example.security.SecurityInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptor = registry.addInterceptor(new SecurityInterceptor());
		interceptor.addPathPatterns("/**");
		interceptor.excludePathPatterns("/login/**");
		interceptor.excludePathPatterns("/static/**");
		interceptor.excludePathPatterns("/regiest/**");
		interceptor.excludePathPatterns("/randomImage/**");
		interceptor.excludePathPatterns("/exit/**");
	}

}