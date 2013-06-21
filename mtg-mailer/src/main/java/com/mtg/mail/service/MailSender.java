package com.mtg.mail.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mtg.mail.dto.Email;

public class MailSender implements Runnable {

    private static Logger log = LoggerFactory.getLogger(MailSender.class);
    private Email email;
    private MailSenderService service;
    
    public MailSender(Email email, MailSenderService service) {
        this.email = email;
        this.service = service;
    }
    
    @Override
    public void run() {
        try {
            service.sendMail(email);
        } catch (IOException e) {
            log.error("Error sending mail. But nothing will be done about it. email={}, exception={}", email, e);
            throw new IllegalArgumentException(e);
        }
    }

}
