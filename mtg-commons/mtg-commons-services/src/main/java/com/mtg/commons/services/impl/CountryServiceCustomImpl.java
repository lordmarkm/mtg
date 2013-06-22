package com.mtg.commons.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.CountryServiceCustom;
import com.mtg.commons.services.ImageService;

@Transactional
public class CountryServiceCustomImpl extends AbstractEntityService implements CountryServiceCustom {

	@Resource
	private ImageService images;
	
	@Resource
	private CountryService service;
	
	@Override
	@SuppressWarnings("deprecation") //only excise(..) may call delete(..) and get away with it
	public void excise(Country country) {
		images.excise(country.getImage());
		images.excise(country.getFlag());
		service.delete(country);
	}

	@Override
	public void exciseAll() {
		List<Country> all = service.findAll();
		for(Country country : all) {
			excise(country);
		}
	}

}
