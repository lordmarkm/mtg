package com.mtg.mail.service.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mtg.mail.dto.Email;
import com.mtg.mail.service.MailSenderService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Resource
    private Configuration config;

    @Resource
    private MailSender mailSender;
    
    @PostConstruct
    public void init() {
        Validate.notNull(config, "Freemarker configuration for email is null!");
    }

    private void validate(Email email) {
        Validate.notNull(email.getTemplate());
        Validate.notNull(email.getSender());
        Validate.notNull(email.getRecipient());
        Validate.notNull(email.getSubject());
    }

//    @Override
//    public void sendMimeMail(Email email) throws IOException {
//        MimeMessagePreparator helper = new MimeMessageHelper();
//    }
    
    @Override
    public void sendMail(Email email) throws IOException {
        
        validate(email);
        // TODO Auto-generated method stub
        SimpleMailMessage message = new SimpleMailMessage();

        final String result;
        Template template = config.getTemplate(email.getTemplate());
        try {
            result = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getModel());
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
        
        message.setFrom(email.getSender());
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(result);
        
        mailSender.send(message);
    }


}
