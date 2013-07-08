package com.mtg.web.controller.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.controller.AccountDeckController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.NewDeckForm;

@Component
public class AccountDeckControllerImpl extends GenericController implements AccountDeckController {

	private static Logger log = LoggerFactory.getLogger(AccountDeckControllerImpl.class);
	
	@Override
	public ModelAndView newdeckForm(Principal principal) {

		log.info("Deck creation page requested. user = {}", name(principal));
		
		return mav("account/deck/new");
	}

	@Override
	public JSON newdeck(Principal principal, NewDeckForm form, BindingResult binding) {
		return null;
	}

}
