package com.mtg.mail.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.mail.dto.Email;
import com.mtg.mail.service.MailSenderService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MailConfiguration.class})
public class MailSenderTest {

    @Resource
    private MailSenderService sender;
    
    @Resource
    private MailSender springSender;
    
    //@Test
    public void sendFromGoogle() throws IOException {
        //sends actual email from gmail account
        Email email = new Email();
        email.setRecipient("lordmarkm@gmail.com");
        email.setSender("mtglol@gmail.com");
        email.setSubject("Hello");
        email.setMessage("World");
        email.getModel().put("name", "Mark");
        email.setTemplate("testmail.ftl");
        
        sender.sendMail(email);
    }
    
}
