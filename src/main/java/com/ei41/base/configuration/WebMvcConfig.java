package com.ei41.base.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// 静态资源映射
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(
				"classpath:/static/");
	}

	// @Override
	// public void configureViewResolvers(ViewResolverRegistry registry) {
	// registry.enableContentNegotiation(new MappingJackson2JsonView());
	// registry.freeMarker().cache(false);
	// }
	//
	// @Bean
	// public FreeMarkerConfigurer freeMarkerConfigurer() {
	// FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
	// configurer.setTemplateLoaderPath("/WEB-INF/");
	// return configurer;
	// }
}
