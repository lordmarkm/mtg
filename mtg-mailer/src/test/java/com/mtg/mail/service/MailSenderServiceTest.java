package com.mtg.mail.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.mail.config.MailConfiguration;
import com.mtg.mail.service.MailSenderService;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MailConfiguration.class})
public class MailSenderServiceTest {

	@Resource
	private MailSenderService senderService;
	
//	@Test
	public void testSendMail() {
		AccountInfo info = new AccountInfo();
		info.setEmail("markbbmartinez@gmail.com");
		info.setAuthenticationCode("12345");
		
		Account account = new Account();
		account.setUsername("markm");
		account.setInfo(info);
		
		senderService.sendWelcomeEmail(account);
	}
	
}
