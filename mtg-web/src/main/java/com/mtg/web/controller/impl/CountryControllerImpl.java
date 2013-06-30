package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.services.CountryService;
import com.mtg.web.controller.CountryController;
import com.mtg.web.controller.GenericController;

@Component
public class CountryControllerImpl extends GenericController implements CountryController {

	private static Logger log = LoggerFactory.getLogger(CountryControllerImpl.class);
	
	@Resource
	private CountryService countries;
	
	@Override
	public ModelAndView browse(Principal principal) {

		log.info("Country list requested. user = {}", name(principal));
		
		List<Country> occupied = countries.findOccupied();

		return mav("country/list").addObject("countries", occupied);
	}

	@Override
	public ModelAndView country(Principal principal, @PathVariable String urlFragment) {
		
		log.info("Country page requested. country = {}, user = {}", urlFragment, name(principal));
		
		Country country = countries.findByUrlFragment(urlFragment);
		
		return mav("country/country").addObject("country", country);
	}

}
