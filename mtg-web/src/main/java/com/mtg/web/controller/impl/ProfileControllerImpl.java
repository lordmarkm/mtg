package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.ProfileController;

@Component
public class ProfileControllerImpl extends GenericController implements ProfileController {

	private static Logger log = LoggerFactory.getLogger(ProfileControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Override
	public ModelAndView profile(Principal principal, @PathVariable String username) {
		
		log.info("Profile page requested. username={}, requestor={}", username, name(principal));
		
		Account user = accounts.findByUsername(username);
		
		return mav("profile")
				.addObject("user", user);
	}

}
