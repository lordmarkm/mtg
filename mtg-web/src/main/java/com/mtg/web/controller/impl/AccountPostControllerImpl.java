package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.mtg.interactive.posts.services.PlayerPostService;
import com.mtg.web.controller.AccountPostController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.JSON;

@Component
public class AccountPostControllerImpl extends GenericController implements AccountPostController {

	private static Logger log = LoggerFactory.getLogger(AccountPostControllerImpl.class);
	
	@Resource
	private PlayerPostService accountPostService;
	
	@Override
	public JSON save(Principal principal, @PathVariable Long id) {
		
		log.info("Post save request. user={}, post={}", name(principal), id);
		
		Validate.notNull(principal);
		accountPostService.save(principal.getName(), id);
		
		return JSON.ok();
	}
	
	@Override
	public JSON unsave(Principal principal, @PathVariable Long id) {
		
		log.info("Unsave request. user={}, post={}", name(principal), id);
		
		Validate.notNull(principal);
		accountPostService.unsave(principal.getName(), id);
		
		return JSON.ok();
	}

}
