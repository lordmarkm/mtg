package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.services.CityService;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.LocationSecurityService;
import com.mtg.web.controller.CityController;
import com.mtg.web.controller.GenericController;

@Component
public class CityControllerImpl extends GenericController implements CityController {

	private static Logger log = LoggerFactory.getLogger(CityControllerImpl.class);
	
	@Resource
	private CityService cities;
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private LocationSecurityService locations;
	
	@Override
	public ModelAndView browse(Principal principal) {
		
		log.info("City list requested. user={}", name(principal));
		
		List<City> allCities = cities.findAllNotBanned();
		return mav("city/list")
				.addObject("cities", allCities);
	}

	@Override
	public ModelAndView city(Principal principal, @PathVariable String urlFragment) {
		
		log.info("City page requested. user={}, city={}", name(principal), urlFragment);
		
		City city = cities.findByUrlFragment(urlFragment);
		return mav("city/city-posts")
				.addObject("city", city);
	}

	@Override
	public ModelAndView players(Principal principal, @PathVariable String urlFragment) {
		log.info("City players page requested. user={}, city={}", name(principal), urlFragment);
		
		City city = cities.findByUrlFragment(urlFragment);
		return mav("city/city-players")
				.addObject("city", city);
	}

	@Override
	public ModelAndView newpost(Principal principal, @PathVariable String urlFragment) {
		log.info("New post form requested. user={}, city={}", name(principal), urlFragment);
		
		City city = cities.findByUrlFragment(urlFragment);
		Validate.notNull(city);
		
		return mav("location/post-form")
				.addObject("location", city)
				.addObject("type", PostParentType.city);
	}

	@Override
	public ModelAndView manage(Principal principal, @PathVariable String urlFragment) {

		log.info("City management page requested. user={}, city={}", name(principal), urlFragment);

		City city = cities.findByUrlFragment(urlFragment);
		locations.ensureModOrAdmin(principal, city);
		
		return mav("location/manage")
				.addObject("location", city)
				.addObject("type", PostParentType.city);
	}
	
}
