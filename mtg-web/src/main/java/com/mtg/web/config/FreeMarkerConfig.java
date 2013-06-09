package com.mtg.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreeMarkerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPath("/WEB-INF/view/");
        return result;
    }
    
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver fmvr = new FreeMarkerViewResolver();
        fmvr.setCache(true);
        fmvr.setPrefix("");
        fmvr.setSuffix(".jsp");
        fmvr.setExposeSpringMacroHelpers(true);
        return fmvr;
    }
    
}
