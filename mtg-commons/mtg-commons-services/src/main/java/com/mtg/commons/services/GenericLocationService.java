package com.mtg.commons.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;

@Service
public class GenericLocationService {

	@Resource
	private CountryService countries;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;

	public Location getLocation(Location.Type type, Long id) {
		switch(type) {
		case country:
			return countries.findOne(id);
		case city:
			return cities.findOne(id);
		case meetup:
			return meetups.findOne(id);
		default:
			return null;
		}
	}
	
	public List<MagicPlayer> getPlayers(Location.Type type, Long id) {
		Location loc = getLocation(type, id);
		return null != loc ? loc.getPlayers() : null;
	}
	
}

