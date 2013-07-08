package com.mtg.interactive.posts.services;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.interactive.posts.services.aop.LocationModificationAspect;
import com.mtg.interactive.posts.services.config.PostsServicesConfig;
import com.mtg.security.config.SecurityConfig;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class,
		SecurityConfig.class, PostsServicesConfig.class, ChatServicesTestConfig.class})
@Transactional
public class InteractiveLocationServiceTest {

	@Resource
	private InteractiveLocationService service;
	
	@Resource
	private CityService cities;
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private LocationModificationAspect aspect;
	
	private City dgte() {
		return cities.save(Util.dgte());
	}
	
	private Account markm() {
		return accounts.save(Util.account());
	}
	
	private Account stranger() {
		return accounts.save(Util.account2());
	}
	
	@Test(expected = AccessDeniedException.class)
	public void directTestFail() throws AccessDeniedException {
		City dgte = dgte();
		Account user1 = accounts.save(stranger());
		aspect.checkAccess(null, dgte, user1.getPlayer());
	}
	
	@Test
	public void testMakemodModerator() {
		City dgte = dgte();
		Account mod = accounts.save(stranger());
		dgte.getModerators().add(mod.getPlayer());
		Account newmod = accounts.save(stranger());
		service.makeModerator(dgte, mod.getPlayer(), newmod.getPlayer());
	}
	
	@Test
	public void testMakemodAdmin() {
		City dgte = dgte();
		Account admin = accounts.save(markm());
		Account newmod = accounts.save(stranger());
		service.makeModerator(dgte, admin.getPlayer(), newmod.getPlayer());
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testMakemodDenied() {
		City dgte = dgte();
		Account user1 = accounts.save(stranger());
		Account user2 = accounts.save(stranger());
		service.makeModerator(dgte, user1.getPlayer(), user2.getPlayer());
	}
}
