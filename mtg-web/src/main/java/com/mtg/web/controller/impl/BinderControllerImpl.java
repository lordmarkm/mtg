package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.locations.Location.Type;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.GenericLocationService;
import com.mtg.commons.services.MeetupService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.BinderController;
import com.mtg.web.controller.GenericController;

@Component
public class BinderControllerImpl extends GenericController implements BinderController {

	private static Logger log = LoggerFactory.getLogger(BinderControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private BinderService bindserv;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private GenericLocationService locations;
	
	/**
	 * Unlike AccountController.player(..), principal here may be null since this controller is unsecured
	 */
	private MagicPlayer player(Principal principal) {
		return null == principal ? null : accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView browse(Principal principal) {
		
		log.info("Binder browse page requested. user={}", name(principal));
		
		MagicPlayer player = player(principal);
		
		return mav("binder/browse")
				.addObject("player", player)
				.addObject("countries", countries.findOccupied())
				.addObject("cities", cities.findOccupied())
				.addObject("meetups", meetups.findOccupied());
	}

	@Override
	public ModelAndView browse(Principal principal, @PathVariable Type filterType, @PathVariable Long id) {
		
		log.info("Filtered binder browse page requested. user={}, filtertype={}, location id={}", name(principal), filterType, id);
		
		Location location = locations.getLocation(filterType, id);
		List<Binder> binders = bindserv.filterByLocation(filterType, id);
		
		return mav("binder/location-filtered")
				.addObject("location", location)
				.addObject("binders", binders);
	}

}

