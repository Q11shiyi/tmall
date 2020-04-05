package org.springboot.tmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvcConfigurerAdapter此类已被废弃,改用WebMvcConfiguration接口
@Configuration
public class CORSConfiguration implements WebMvcConfigurer{
	  @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        //所有请求都允许跨域
	        registry.addMapping("/**")
	                .allowedOrigins("*")
	                .allowedMethods("*")
	                .allowedHeaders("*");
	    }
}
