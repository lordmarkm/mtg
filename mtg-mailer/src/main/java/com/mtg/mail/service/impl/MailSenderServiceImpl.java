package com.mtg.mail.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mtg.mail.dto.Email;
import com.mtg.mail.service.MailSenderService;
import com.mtg.mail.service.MailSenderThread;
import com.mtg.security.models.Account;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@PropertySource("classpath:mail.properties")
public class MailSenderServiceImpl implements MailSenderService {

	private static Logger log = LoggerFactory.getLogger(MailSenderServiceImpl.class);
	
    @Resource
    private Configuration config;

    @Resource
    private JavaMailSender mailSender;
    
    @Resource
    private TaskExecutor taskExecutor;
    
    @Resource
    private Environment env;
    
    @PostConstruct
    public void init() {
        Validate.notNull(config, "Freemarker configuration for email is null!");
    }

    @Override
    public void sendMimeMail(Email email) throws IOException, MessagingException, TemplateException {
    	MimeMessage message = mailSender.createMimeMessage();
    	
    	Template template = config.getTemplate(email.getTemplate());
    	
    	Validate.notNull(email.getModel().get(("activationLink")));
    	Validate.notNull(email.getModel().get("name"));
    	
    	String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getModel());
    	message.setContent(content, "text/html");
    	
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	helper.setFrom(email.getSender());
    	helper.setTo(email.getRecipient());
    	helper.setSubject(email.getSubject());
    	
    	mailSender.send(message);
    }
    
    private Email assembleEmail(Account account) {
    	Email email = new Email();

        email.setRecipient(account.getInfo().getEmail());
        email.setSender("mtgbinder@gmail.com");
        return email;
    }
    
	@Override
	public void sendWelcomeEmail(Account account) {
        Email welcome = assembleEmail(account);
        welcome.setSubject("Welcome to MtG Binder!");
        welcome.setTemplate("welcome.ftl");
        
		String baseUrl = env.getProperty("app.base.url");
		String activationPath = env.getProperty("app.activation.path");
		Validate.notNull(baseUrl);
		Validate.notNull(activationPath);
		
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("activationLink", baseUrl + activationPath + account.getInfo().getAuthenticationCode());
        model.put("name", account.getUsername());
        welcome.setModel(model);
        
        MailSenderThread thread = new MailSenderThread(welcome, this);
        taskExecutor.execute(thread);
	}
	
	@Override
	public void resendVerificationEmail(Account account) {
		log.info("About to resend email verification mail. user={}", account.getUsername());
		
		Email resend = assembleEmail(account);
		resend.setSubject("Mtg Binder email verification");
		resend.setTemplate("verify.ftl");
		
		String baseUrl = env.getProperty("app.base.url");
		String activationPath = env.getProperty("app.activation.path");
		Validate.notNull(baseUrl);
		Validate.notNull(activationPath);
		
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("activationLink", baseUrl + activationPath + account.getInfo().getAuthenticationCode());
        model.put("name", account.getUsername());
        resend.setModel(model);
        
        MailSenderThread thread = new MailSenderThread(resend, this);
        taskExecutor.execute(thread);
	}


}
