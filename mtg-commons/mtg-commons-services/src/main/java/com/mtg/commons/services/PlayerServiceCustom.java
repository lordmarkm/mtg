package com.mtg.commons.services;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.magic.MagicPlayer;

public interface PlayerServiceCustom {

	/**
	 * Add an existing city
	 */
	void addCity(MagicPlayer player, Long cityId);

	/**
	 * Create a new city then add
	 */
	MagicPlayer newCity(MagicPlayer player, String cityName, String cityDesc,
			Long countryId);

	/**
	 * Removes city from player's list of cities, then if the city has no more players, deletes it
	 */
	void removeCity(MagicPlayer player, Long cityId);

	/**
	 * Add an existing meetup
	 */
	void addMeetup(MagicPlayer player, Long id);
	
	/**
	 * Create a new meetup then add
	 * @return 
	 */
	MagicPlayer newMeetup(MagicPlayer player, String meetupName, String meetupDesc,
			Long cityId);
	
	/**
	 * Removes meetup from player's list of meetups, then if the meetup has no more players, deletes it
	 */
	void removeMeetup(MagicPlayer player, Long meetupId);
	
	/**
	 * Set a new country
	 */
	void changeFlag(MagicPlayer player, Country country);

	/**
	 * Remove flag
	 */
	void removeFlag(MagicPlayer player);

}
