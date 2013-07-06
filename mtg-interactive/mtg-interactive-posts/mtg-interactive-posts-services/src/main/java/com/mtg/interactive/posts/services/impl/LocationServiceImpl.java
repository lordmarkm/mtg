package com.mtg.interactive.posts.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.locations.Location.Type;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.interactive.posts.services.LocationService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Resource
	private CountryService countries;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private AccountService accounts;
	
	@Override
	public void makemod(Account requestor, Type type, Long id, Long playerId) {
		Validate.notNull(requestor);
		
		MagicPlayer newMod = accounts.findOne(playerId).getPlayer();
		Validate.notNull(newMod);
		
		Location location = null;
		switch(type) {
		case country:
			location = countries.findOne(id);
			break;
		case city:
			location = cities.findOne(id);
			break;
		case meetup:
			location = meetups.findOne(id);
			break;
		default:
			throw new IllegalArgumentException("Unsupported location type for makemod: " + type);
		}
		Validate.notNull(location);
		
		if(!Roles.hasRole(requestor, Roles.ROLE_ADMIN) && !location.getModerators().contains(requestor.getPlayer())) {
			throw new AccessDeniedException("No! I am too sexy for you.");
		} else {
			location.getModerators().add(newMod);
		}
	}

}
