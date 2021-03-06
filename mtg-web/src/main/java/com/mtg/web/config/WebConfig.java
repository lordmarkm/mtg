package com.mtg.web.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mtg.web.controller.CityController;
import com.mtg.web.controller.CommentController;
import com.mtg.web.controller.CountryController;
import com.mtg.web.controller.MeetupController;
import com.mtg.web.controller.PostController;
import com.mtg.web.interceptor.LocationInterceptor;
import com.mtg.web.interceptor.NavbarInterceptor;
import com.mtg.web.interceptor.OnePageInterceptor;
import com.mtg.web.interceptor.PostOrCommentInterceptor;
import com.mtg.web.interceptor.UsernameInjectingInterceptor;
import com.mtg.web.support.DbMessageSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.mtg.web")
@PropertySource({"classpath:app.properties", "classpath:mvc.properties"})
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private DbMessageSource messageSource;
	
	@Resource
	private NavbarInterceptor navbarInterceptor;
	
	@Resource
	private OnePageInterceptor onepageInterceptor;
	
	@Resource
	private LocationInterceptor locationInterceptor;
	
	@Resource
	private PostOrCommentInterceptor postOrCommentInterceptor;
	
	@Resource
	private UsernameInjectingInterceptor usernameInjector;
	
    @Bean
    public MessageSource messageSource() {
    	return messageSource;
    }
    
    @Bean
    public LocalValidatorFactoryBean defaultValidator() {
    	return new LocalValidatorFactoryBean();
    }
    
    /**
     * Should be equivalent to
     * <mvc:resources mapping="/css/**" location="/css/"/> 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    }
    
    /**
     * Should be equivalent to 
     * <mvc:interceptors>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(usernameInjector);
        registry.addInterceptor(navbarInterceptor);
    	registry.addInterceptor(onepageInterceptor);
    	registry.addInterceptor(postOrCommentInterceptor)
    	    .addPathPatterns(CommentController.PATTERNS)
    	    .addPathPatterns(PostController.PATTERNS);
    	registry.addInterceptor(locationInterceptor)
    		.addPathPatterns(MeetupController.PATTERNS)
    		.addPathPatterns(CityController.PATTERNS)
    		.addPathPatterns(CountryController.PATTERNS);
    }
}
