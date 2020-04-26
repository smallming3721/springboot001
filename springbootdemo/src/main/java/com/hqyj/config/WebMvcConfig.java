package com.hqyj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.filter.MyFilter;
import com.hqyj.interceptor.MyInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private MyInterceptor myInterceptor;
	@Bean
	public FilterRegistrationBean<MyFilter> filterRegistrationBean(){
		FilterRegistrationBean<MyFilter> filterRegistration=new FilterRegistrationBean<>();
		filterRegistration.setFilter(new MyFilter());
		return filterRegistration;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor).addPathPatterns("/**");
	}
	
	
}
