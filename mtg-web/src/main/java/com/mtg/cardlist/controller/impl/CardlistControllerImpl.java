package com.mtg.cardlist.controller.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.cardlist.controller.CardlistController;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.dtos.BundleDto;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.PlayerService;
import com.mtg.web.controller.GenericController;

/**
 * @author mbmartinez
 */
@Component
public class CardlistControllerImpl extends GenericController implements CardlistController {

	private static Logger log = LoggerFactory.getLogger(CardlistControllerImpl.class);
	
	@Resource
	private PlayerService players;

	@Override
    public ModelAndView cardlist(Principal principal) {

		if (null == principal) {
			return redirect("/auth/login");
		}
		
		log.debug("Viewing own cardlist. User={}", principal);
		return cardlist(principal, principal.getName());
    }

	@Override
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ModelAndView cardlist(Principal principal, String username) {
        return mav("cardlist/cardlist")
        		.addObject("username", username);
	}

	@Override
	public List<BundleDto> userCards(Principal principal, @PathVariable String username) {

		log.debug("Retrieving all cards for username={}, requestor={}", username, name(principal));
		
		MagicPlayer player = players.findByName(username);

		List<Bundle> bundles = new ArrayList<Bundle>();
		for (Binder binder : player.getBinders()) {
			for (BinderPage page : binder.getPages()) {
				bundles.addAll(page.getBundles());
			}
		}

		return BundleDto.toDtos(bundles);
	}

}
