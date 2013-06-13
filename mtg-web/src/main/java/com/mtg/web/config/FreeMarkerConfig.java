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
        FreeMarkerViewResolver res = new FreeMarkerViewResolver();
        res.setCache(true);
        res.setPrefix("");
        res.setSuffix(".jsp");
        res.setExposeSpringMacroHelpers(true);
        return res;
    }
    
}
