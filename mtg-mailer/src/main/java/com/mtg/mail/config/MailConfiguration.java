package com.mtg.mail.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@org.springframework.context.annotation.Configuration
@ComponentScan("com.mtg.mail")
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

    @Resource
    private Environment env;

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(Integer.valueOf(env.getProperty("mail.smtp.port")));
        mailSender.setHost(env.getProperty("mail.smtp.host"));
        mailSender.setUsername(env.getProperty("mail.smtp.username"));
        mailSender.setPassword(env.getProperty("mail.smtp.password"));
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        mailSender.setJavaMailProperties(javaMailProperties);
        
        return mailSender;
    }
    
    @Bean
    public Configuration config() throws IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean configFactory = new FreeMarkerConfigurationFactoryBean();
        configFactory.setTemplateLoaderPath(env.getProperty("mail.templatedir"));
        configFactory.setPreferFileSystemAccess(false);
        Configuration config = configFactory.createConfiguration();
        Validate.notNull(config);
        return config;
    }
    
}
