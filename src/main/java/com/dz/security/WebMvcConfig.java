package com.dz.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dz.interceptor.ThymeleafLayoutInterceptor;
/**
 * 自定义相关的mvc 配置
 * @author qlz
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
		//增加模板处理器
	        registry.addInterceptor(new ThymeleafLayoutInterceptor());
	    }

}
