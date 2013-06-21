package com.mtg.mail.service;

import java.io.IOException;

import com.mtg.mail.dto.Email;

public interface MailSenderService {

    public void sendMail(Email email) throws IOException;
    
}
