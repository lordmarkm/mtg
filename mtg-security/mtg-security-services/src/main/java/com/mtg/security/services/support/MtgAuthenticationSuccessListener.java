package com.mtg.security.services.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.mtg.audit.service.AuditLogger;
import com.mtg.audit.support.AuditableEvent;
import com.mtg.security.services.AccountService;

@Component
public class MtgAuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private static Logger log = LoggerFactory.getLogger(MtgAuthenticationSuccessListener.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private AuditLogger audit;
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		
		Authentication auth = event.getAuthentication();
		log.info("Authentication success event. auth={}", auth);

		User user = (User) auth.getPrincipal();
		accounts.updateLastLogin(user.getUsername());
		
		audit.log(AuditableEvent.user_login, user.getUsername());
	}

}
