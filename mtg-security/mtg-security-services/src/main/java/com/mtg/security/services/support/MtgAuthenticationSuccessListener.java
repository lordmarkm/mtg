package com.mtg.security.services.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.mtg.security.services.AccountService;

@Component
public class MtgAuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private static Logger log = LoggerFactory.getLogger(MtgAuthenticationSuccessListener.class);
	
	@Resource
	private AccountService accounts;
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		
		Authentication auth = event.getAuthentication();
		log.info("Authentication success event. auth={}", auth);

		User user = (User) auth.getPrincipal();
		accounts.updateLastLogin(user.getUsername());
	}

}
