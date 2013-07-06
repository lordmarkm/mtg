package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Location.Type;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.interactive.posts.services.LocationService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.LocationController;
import com.mtg.web.dto.JSON;

@Component
public class LocationControllerImpl extends GenericController implements LocationController {

	private static int RELATED_SIZE = 10;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;

	@Resource
	private AccountService accounts;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private LocationService locations;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView communities(Principal principal) {
		
		ModelAndView mav = mav("community/browse");
		
		List<Country> allCountries = countries.findOccupied();
		List<City> allCities = cities.findAllNotBanned();
		List<Meetup> allMeetups = meetups.findAllNotBanned();
		
		if(null != principal) {
			MagicPlayer player = player(principal);
			mav.addObject("player", player);
			
			Set<City> relatedCities = relatedCities(player);
			Set<Meetup> relatedMeetups = relatedMeetups(player);
			
			mav.addObject("relatedCities", relatedCities)
			   .addObject("relatedMeetups", relatedMeetups);

			if(null != player.getCountry()) {
				allCountries.remove(player.getCountry());
			}
			
			allCities.removeAll(player.getCities());
			allCities.removeAll(relatedCities);
			
			allMeetups.removeAll(player.getMeetups());
			allMeetups.removeAll(relatedMeetups);
		} 
		
		return mav.addObject("countries", allCountries)
				.addObject("cities", allCities)
				.addObject("meetups", allMeetups);
	}
	
	private Set<Meetup> relatedMeetups(MagicPlayer player) {
		Set<Meetup> relatedMeetups = new HashSet<Meetup>();
		List<Meetup> playerMeetups = player.getMeetups();
		
		//meetups in the same cities as the player
		cityloop:
		for(City city : player.getCities()) {
			for(Meetup meetup : city.getMeetups()) {
				if(playerMeetups.contains(meetup)) continue;
				if(relatedMeetups.size() >= RELATED_SIZE) break cityloop;
				relatedMeetups.add(meetup);
			}
		}
		return relatedMeetups;
	}
	
	private Set<City> relatedCities(MagicPlayer player) {
		Set<City> relatedCities = new HashSet<City>();
		
		//other cities in the player's country
		Country country = player.getCountry();
		if(null != country) {
			for(City city : country.getCities()) {
				if(relatedCities.size() >= RELATED_SIZE) break;
				if(city.isBanned()) continue;
				if(player.getCities().contains(city)) continue;
			
				relatedCities.add(city);
			}
		}
		
		//cities in the same country as cities the player is in that are not in the player's country
		for(City city : player.getCities()) {
			if(city.getCountry() != null && !city.getCountry().equals(country)) {
				for(City cCity : city.getCountry().getCities()) {
					if(relatedCities.size() >= RELATED_SIZE) break;
					if(cCity.isBanned()) continue;
					if(player.getCities().contains(cCity)) continue;
				
					relatedCities.add(cCity);
				}
			}
		}
		
		return relatedCities;
	}

	@Override
	public JSON makemod(Principal principal, @PathVariable Type type, @PathVariable Long id, @PathVariable Long playerId) {
		Validate.notNull(principal);
		locations.makemod(account(principal), type, id, playerId);
		return JSON.ok();
	}

	private Account account(Principal principal) {
		return null == principal ? null : accounts.findByUsername(principal.getName());
	}

}
