package com.mtg.security.services;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import testconfig.TestPersistenceConfig;

import com.mtg.commons.services.CityService;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.mail.service.MailSenderService;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsServicesConfig.class, TestPersistenceConfig.class})
@Transactional 
public class AccountServiceTest {

	private static Logger log = LoggerFactory.getLogger(AccountServiceTest.class);
	
	@Mock
	private MailSenderService mailer;
	
	@InjectMocks
	@Resource
	private AccountService accounts;
	
	@Resource
	private CityService cities;
	
	@After
	public void clear() {
		accounts.deleteAll();
	}
	
	@Test
	public void configLoaded() {
		//
	}
	
	@Test
	public void testSave() {
		Account a = Util.account();
		
		Account s = accounts.save(a);
		assertNotNull(s.getInfo());
		assertNotNull(s.getInfo().getJoined());
		assertNotNull(s.getInfo().getLastLogin());
		
		AccountInfo info = s.getInfo();
		log.info("joined={}", info.getJoined());
		log.info("last login={}", info.getLastLogin());
	}
	
}
