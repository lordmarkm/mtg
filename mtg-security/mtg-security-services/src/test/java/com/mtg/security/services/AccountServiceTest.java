package com.mtg.security.services;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.services.CityService;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.security.config.PersistenceConfig;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsServicesConfig.class, PersistenceConfig.class})
public class AccountServiceTest {

	private static Logger log = LoggerFactory.getLogger(AccountServiceTest.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private CityService cities;
	
	@Before
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
