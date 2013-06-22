package com.mtg.commons.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Wanted;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CardService;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.PlayerServiceCustom;

@Transactional
public class PlayerServiceCustomImpl implements PlayerServiceCustom {
	
	private static Logger log = LoggerFactory.getLogger(PlayerServiceCustomImpl.class);
	
	@Resource
	private PlayerService service;
	
	@Resource
	private CityService cities;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private CardService cards;
	
	private boolean addCity(MagicPlayer player, City city) {
		Validate.notNull(city);
		Validate.notNull(player);
		
		if(player.getCities().contains(city)) {
			log.warn("Player cities: {}", player.getCities());
			log.warn("City: {}", city);
			log.warn("Skipping duplicate city");
			return false;
		}
		
		player.getCities().add(city);
		city.getPlayers().add(player);
		return true;
	}
	
	private boolean addMeetup(MagicPlayer player, Meetup meetup) {
		Validate.notNull(meetup);
		Validate.notNull(player);
		
		if(player.getMeetups().contains(meetup)) {
			log.warn("Skipping duplicate meetup");
			return false;
		}
		
		player.getMeetups().add(meetup);
		meetup.getPlayers().add(player);
		return true;
	}
	
	@Override
	public void addCity(MagicPlayer player, Long cityId) {
		
		City city = cities.findOne(cityId);
		
		if(addCity(player, city)) {
			cities.save(city); //needs separate save, there is no cascade player->city
			service.save(player);
		}
	}

	@Override
	public MagicPlayer newCity(MagicPlayer player, String cityName, String cityDesc,
			Long countryId) {
		
		if(null == countryId) countryId = 0L;
		Country country = countries.findOne(countryId);
		
		City city = new City();
		city.setName(cityName);
		city.setDescription(cityDesc);
		city.setCountry(country);
		
		if(addCity(player, city)) {
			cities.save(city); //needs separate save, there is no cascade player->city
			return service.save(player);
		}
		
		return player;
	}

	@Override
	public void removeCity(MagicPlayer player, Long cityId) {
		
		City city = cities.findOne(cityId);
		Validate.notNull(player);
		Validate.notNull(city);
		
		player.getCities().remove(city);
		service.save(player);
		
		city.getPlayers().remove(player);
		if(city.getPlayers().size() == 0 && city.getMeetups().size() == 0) {
			cities.delete(city);
		} else {
			cities.save(city);
		}
		
	}
	
	@Override
	public void addMeetup(MagicPlayer player, Long meetupId) {
		
		Meetup meetup = meetups.findOne(meetupId);
		
		if(addMeetup(player, meetup)) {
			meetups.save(meetup); //needs separate save, there is no cascade player->meetup
			service.save(player);
		}
	}
	
	@Override
	public MagicPlayer newMeetup(MagicPlayer player, String meetupName,
			String meetupDesc, Long cityId) {
		if(null == cityId) cityId = 0L;
		City city = cities.findOne(cityId);
		
		Meetup meetup = new Meetup();
		meetup.setName(meetupName);
		meetup.setDescription(meetupDesc);
		meetup.setCity(city);
		
		if(addMeetup(player, meetup)) {
			meetups.save(meetup);
			return service.save(player);
		}
		
		return player;
	}
	
	@Override
	public void removeMeetup(MagicPlayer player, Long meetupId) {
		
		Meetup meetup = meetups.findOne(meetupId);
		Validate.notNull(player);
		Validate.notNull(meetup);
		
		player.getMeetups().remove(meetup);
		service.save(player);
		
		meetup.getPlayers().remove(player);
		if(meetup.getPlayers().size() == 0) {
			meetups.delete(meetup);
		} else {
			meetups.save(meetup);
		}
		
	}

	@Override
	public void changeFlag(MagicPlayer player, Country country) {
		player.setCountry(country);
		service.save(player);
	}

	@Override
	public void removeFlag(MagicPlayer player) {
		player.setCountry(null);
		service.save(player);
	}

	@Override
	public boolean addToWantlist(MagicPlayer player, Long id) {
		Card card = cards.findOne(id);
		
		Validate.notNull(player);
		Validate.notNull(card);
		
		List<Wanted> wantlist = player.getWanted();
		for(Wanted wanted : wantlist) {
			if(wanted.getCard().equals(card)) return false;
		}
		
		Wanted wanted = new Wanted(card);
		wanted.setWanter(player);
		wanted.setCount(1);
		wanted.setLastModified(DateTime.now());
		
		wantlist.add(wanted);
		service.save(player);
		
		return true;
	}

}
