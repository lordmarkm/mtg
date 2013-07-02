package com.mtg.commons.services;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.service.support.LastModeratorCantLeaveException;

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
	 * Removes city from player's list of cities, then if the city has no more players and no meetups, deletes 
	 * @throws LastModeratorCantLeaveException 
	 */
	void removeCity(MagicPlayer player, Long cityId) throws LastModeratorCantLeaveException;

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
	 * @throws LastModeratorCantLeaveException 
	 */
	void removeMeetup(MagicPlayer player, Long meetupId) throws LastModeratorCantLeaveException;
	
	/**
	 * Set a new country
	 */
	void changeFlag(MagicPlayer player, Country country);

	/**
	 * Remove flag
	 */
	void removeFlag(MagicPlayer player);

	/**
	 * Wantlist ops
	 * @return true if add successful, false if already in wantlist
	 */
	boolean addToWantlist(MagicPlayer player, Long id);

}
