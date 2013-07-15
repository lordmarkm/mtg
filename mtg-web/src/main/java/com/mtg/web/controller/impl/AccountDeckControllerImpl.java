package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.collections.Deck;
import com.mtg.commons.services.DeckService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountDeckController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.NewDeckForm;

@Component
public class AccountDeckControllerImpl extends GenericController implements AccountDeckController {

	private static Logger log = LoggerFactory.getLogger(AccountDeckControllerImpl.class);
	
	@Resource
	private DeckService decks;
	
	@Resource
	private AccountService accounts;
	
	@Override
	public ModelAndView newdeckForm(Principal principal) {

		log.info("Deck creation page requested. user = {}", name(principal));
		
		return mav("account/deck/new");
	}

	@Override
	public JSON newdeck(Principal principal, NewDeckForm form, BindingResult binding) {
		
		log.info("Deck creation form received. user={}, deck={}", name(principal), form.toDeck());
		
		if(binding.hasErrors()) {
			return JSON.error(firstError(binding));
		}
		
		Deck deck = decks.create(principal.getName(), form.toDeck());
		
		return JSON.ok(deck.getId() + "/" + deck.getUrlFragment());
	}

	@Override
	public ModelAndView edit(Principal principal, @PathVariable Long id, @PathVariable String urlFragment) {

		Deck deck = decks.findOne(id);
		Validate.notNull(deck);

		return mav("/account/deck/edit")
				.addObject("deck", deck);
	}

}
