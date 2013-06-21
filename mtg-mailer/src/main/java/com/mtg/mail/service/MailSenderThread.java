package com.mtg.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mtg.mail.dto.Email;

public class MailSenderThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(MailSenderThread.class);
    private Email email;
    private MailSenderService service;
    
    public MailSenderThread(Email email, MailSenderService service) {
        this.email = email;
        this.service = service;
    }
    
    @Override
    public void run() {
        try {
        	log.info("Sending email to newly registered user. email={}", email.getRecipient());
            service.sendMimeMail(email);
        } catch (Exception e) {
            log.error("Error sending mail. But nothing will be done about it. email={}", email, e);
            throw new IllegalArgumentException(e);
        }
    }

}
