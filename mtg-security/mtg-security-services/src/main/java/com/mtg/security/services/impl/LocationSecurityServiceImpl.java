package com.mtg.security.services.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.mtg.commons.models.locations.Location;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.LocationSecurityService;
import com.mtg.security.services.support.Roles;

@Service
public class LocationSecurityServiceImpl implements LocationSecurityService {

	@Resource
	private AccountService accounts;
	
	@Override
	public void ensureModOrAdmin(Principal principal, Location location) {
		//deny not authenticated
		if(null == principal) {
			throw new AccessDeniedException("No! I am too sexy for you.");
		}

		//deny if neither mod nor admin
		Account account = accounts.findByUsername(principal.getName());
		if(!location.getModerators().contains(account.getPlayer()) 
				&& !Roles.hasRole(account, Roles.ROLE_ADMIN)) {
			throw new AccessDeniedException("No! I am too sexy for you.");
		}
	}

}
