package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.service.support.LastModeratorCantLeaveException;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.commons.services.PlayerService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountLocationController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.AddCityForm;
import com.mtg.web.dto.AddMeetupForm;
import com.mtg.web.dto.JSON;
import com.mtg.web.support.Propkeys;

@Component
public class AccountLocationControllerImpl extends GenericController implements AccountLocationController {

	private static Logger log = LoggerFactory.getLogger(AccountLocationControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private CityService cities;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private Environment env;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView addCity(Principal principal) {
		
		log.info("Add city request. user={}", name(principal));
		
		return mav("account/addcity")
				.addObject("cities", cities.findAll())
				.addObject("countries", countries.findAll())
				.addObject("form", new AddCityForm());
	}
	
	@Override
	public JSON addCity(Principal principal, @Valid AddCityForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return JSON.error(firstError(result));
		}
		
		MagicPlayer player = player(principal);
		
		String maxCities = env.getProperty(Propkeys.maxCities);
		Validate.notNull(maxCities);
		
		int max = Integer.valueOf(maxCities);
		if(player.getCities().size() >= max) {
			return JSON.ok();
		}
		
		Long id = form.getCityId();
		if(null != form.getCityId()) {
			//add existing city
			players.addCity(player, id);
		} else {
			//create new city then add
			//reject if name/description null
			String cityName = form.getName();
			String cityDesc = form.getDescription();
			
			if(null == cityName || cityName.trim().length() == 0) {
				return JSON.error("City must have a name.");
			} else if(null == cityDesc || cityDesc.trim().length() == 0) {
				return JSON.error("City must have a short description");
			}
			
			players.newCity(player, cityName, cityDesc, form.getCountryId());
		}
		
		return JSON.ok();
	}

	@Override
	public JSON removeCity(Principal principal, @PathVariable Long cityId) {
		log.info("Remove city request. user={}, city id={}", name(principal), cityId);
		
		MagicPlayer player = player(principal);
		
		try {
			players.removeCity(player, cityId);
		} catch (LastModeratorCantLeaveException e) {
			return JSON.error("Assign a new moderator before leaving");
		}
		
		return JSON.ok();
	}

	@Override
	public ModelAndView addMeetup(Principal principal) {
		log.info("Add meetup request. user={}", name(principal));
		
		return mav("account/addmeetup")
				.addObject("cities", cities.findAll())
				.addObject("meetups", meetups.findAll())
				.addObject("form", new AddMeetupForm());
	}

	@Override
	public JSON addMeetup(Principal principal, AddMeetupForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return JSON.error(firstError(result));
		}
		
		MagicPlayer player = player(principal);
		
		String maxMeetups = env.getProperty(Propkeys.maxMeetups);
		Validate.notNull(maxMeetups);
		
		int max = Integer.valueOf(maxMeetups);
		if(player.getMeetups().size() >= max) {
			return JSON.ok();
		}
		
		Long id = form.getMeetupId();
		if(null != id) {
			//add existing meetup
			players.addMeetup(player, id);
		} else {
			//create new meetup then add
			//reject if name/description null
			String meetupName = form.getName();
			String meetupDesc = form.getDescription();
			
			if(null == meetupName || meetupName.trim().length() == 0) {
				return JSON.error("Meetup must have a name.");
			} else if(null == meetupDesc || meetupDesc.trim().length() == 0) {
				return JSON.error("Meetup must have a short description");
			}
			
			players.newMeetup(player, meetupName, meetupDesc, form.getCityId());
		}
		
		return JSON.ok();
	}

	@Override
	public JSON removeMeetup(Principal principal, @PathVariable Long meetupId) {
		log.info("Remove meetup request. user={}, meetup id={}", name(principal), meetupId);
		
		MagicPlayer player = player(principal);
		try {
			players.removeMeetup(player, meetupId);
		} catch (LastModeratorCantLeaveException e) {
			return JSON.error("Assign a new moderator before leaving");
		}
		
		return JSON.ok();
	}

	@Override
	public JSON selectFlag(Principal principal, @PathVariable Long countryId) {
		log.info("Set country request. user={}, countryId={}", name(principal), countryId);
		
		Country country = countries.findOne(countryId);
		MagicPlayer player = player(principal);
		
		if(null == country && 0 == countryId) {
			players.removeFlag(player);
			return JSON.ok();
		}
		
		Validate.notNull(country);
		Validate.notNull(player);

		players.changeFlag(player, country);
		
		return JSON.ok().put("flag", country.getFlag());
	}
}
