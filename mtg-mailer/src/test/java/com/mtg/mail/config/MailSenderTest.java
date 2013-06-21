package com.mtg.mail.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.mail.dto.Email;
import com.mtg.mail.service.MailSenderService;

import freemarker.template.TemplateException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MailConfiguration.class})
public class MailSenderTest {

    @Resource
    private MailSenderService sender;
    
    @Resource
    private MailSender springSender;
    
//    @Test
    public void sendFromGoogle() throws IOException, MessagingException, TemplateException {
        //sends actual email from gmail account
        Email email = new Email();
        email.setRecipient("markbbmartinez@gmail.com");
        email.setSender("mtglol@gmail.com");
        email.setSubject("Hello");
        email.setMessage("World");
        email.getModel().put("name", "Mark");
        email.getModel().put("activationLink", "http://localhost:8080/activate");
        email.setTemplate("welcome.ftl");
        
        sender.sendMimeMail(email);
    }
    
}
