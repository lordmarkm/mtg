package com.mtg.web.controller.impl;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Image;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.PlayerService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;

@Component
public class AccountControllerImpl extends GenericController implements AccountController {

	private static Logger log = LoggerFactory.getLogger(AccountControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private CountryService countries;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView activate(Principal principal, @PathVariable String activationCode) {
		if(accounts.authenticate(activationCode)) {
			ModelAndView mav = mav(principal != null ? "support/generic-message" : "login")
					.addObject("type", "success")
					.addObject("message", "Verification successful. Your e-mail address is now verified.");
			return mav;
		} else {
			ModelAndView mav = mav(principal != null ? "support/generic-message" : "login")
					.addObject("type", "error")
					.addObject("message", "Invalid verification code, or email already verified. In case of an invalid " +
							"code, you may have a new one sent to your email from your account dashboard.");
			return mav;
		}
	}
	
	@Override
	public ModelAndView dashboard(Principal principal) {
		
		log.info("Dashboard requested. user={}", name(principal));
		
		Account account = accounts.findByUsername(principal.getName());
		return mav("account/dashboard")
				.addObject("account", account)
				.addObject("countries", countries.findAll());
	}

	@Override
	public JSON uploadProfilePic(Principal principal, ImageForm form) throws IOException {

		log.info("Upload profile pic requested. user={}", name(principal));

		Image pic = accounts.saveProfilePic(principal.getName(), form.getData().getBytes());
		
		return JSON.ok().put("image", pic);
	}

	@Override
	public JSON editContact(Principal principal, @RequestParam String contact) {

		log.info("Contact edit request. user={}, contact={}", name(principal), contact);
		
		players.setContact(player(principal).getName(), basic(contact));
		
		return JSON.ok();
	}

}
