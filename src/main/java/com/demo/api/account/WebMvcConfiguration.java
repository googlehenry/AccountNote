package com.demo.api.account;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**").addResourceLocations("file:C://qinjinxiao/web/image/");
		registry.addResourceHandler("/v1/esc/images/**").addResourceLocations("file:/root/web/image/accountcategory/");
		super.addResourceHandlers(registry);
	}

}
