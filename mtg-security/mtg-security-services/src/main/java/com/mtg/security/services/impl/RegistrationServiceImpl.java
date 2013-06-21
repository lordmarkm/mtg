package com.mtg.security.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.mail.dto.Email;
import com.mtg.mail.service.MailSender;
import com.mtg.mail.service.MailSenderService;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.RegistrationService;
import com.mtg.security.services.support.Roles;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private AccountService accounts;
    
    @Resource
    private MailSenderService mailer;
    
    @Resource
    private TaskExecutor taskExecutor;
    
    @Override
    public Account register(String username, String password, String email) {
        
        MagicPlayer player = new MagicPlayer();
        player.setName(username);
        
        AccountInfo info = new AccountInfo();
        info.setJoined(DateTime.now());
        info.setEmail(email);
        info.setAuthenticationCode(RandomStringUtils.randomAlphanumeric(15));
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setAuthorities(Roles.ROLE_USER);
        account.setPlayer(player);
        account.setInfo(info);

        Email welcome = new Email();
        welcome.getModel().put("message", "Welcome to MtG Binder! We will be getting a better welcome email soon!");
        welcome.getModel().put("name", username);
        welcome.setRecipient(email);
        welcome.setSender("mtgbinder@gmail.com");
        welcome.setSubject("Welcome to MtG Binder!");
        welcome.setTemplate("welcome.ftl");
        
        MailSender thread = new MailSender(welcome, mailer);
        taskExecutor.execute(thread);
        
        return accounts.save(account);
        
    }

}
