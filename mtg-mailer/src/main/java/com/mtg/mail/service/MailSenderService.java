package com.mtg.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.mtg.mail.dto.Email;
import com.mtg.security.models.Account;

import freemarker.template.TemplateException;

public interface MailSenderService {

    void sendMimeMail(Email email) throws IOException, MessagingException, TemplateException;
	void sendWelcomeEmail(Account account);
    
}
