package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.collections.Wanted;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.WantedService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountWantlistController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.DtoMaker;
import com.mtg.web.dto.JSON;
import com.mtg.web.support.BundleOperation;

@Component
public class AccountWantlistControllerImpl extends GenericController implements AccountWantlistController {

	private static Logger log = LoggerFactory.getLogger(AccountWantlistControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private WantedService wanteds;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView load(Principal principal) {
		MagicPlayer player = player(principal);
		
		return mav("account/wantlist")
				.addObject("player", player);
	}
	
	@Override
	public JSON add(Principal principal, @PathVariable Long id) {
		MagicPlayer player = player(principal);
		if(players.addToWantlist(player, id)) {
			return JSON.ok();
		} else {
			return JSON.error();
		}
	}

	/**
	 * TODO security hole here. Anybody can decrement
	 */
	@Override
	public JSON op(Principal principal, @PathVariable BundleOperation op, @PathVariable Long id) {
		
		log.info("Wanted list operation request. user={}, op={}, wanted id={}", name(principal), op, id);
		
		switch(op) {
		case increment:
			wanteds.increment(id);
			break;
		case decrement:
			wanteds.decrement(id);
			break;
		case delete:
			wanteds.excise(id);
			break;
		}
		
		return JSON.ok().put("wanted", DtoMaker.transform(wanteds.findOne(id)));
	}

	/**
	 * TODO security hole here. Anybody can decrement - fixed (pass name too), can do same for op(..)
	 */
	@Override
	public JSON editnote(Principal principal, @PathVariable Long id, @RequestParam String note) {

		log.info("Wanted item edit note request. user={}, wanted id={}, note={}");
		
		wanteds.editnote(id,  note);

		return JSON.ok().put("wanted", DtoMaker.transform(wanteds.findOne(id)));
	}

}
