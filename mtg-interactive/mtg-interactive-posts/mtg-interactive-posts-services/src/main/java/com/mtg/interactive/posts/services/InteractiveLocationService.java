package com.mtg.interactive.posts.services;

import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;

public interface InteractiveLocationService {

	/**
	 * @param location
	 * @param adminOrMod - needed for AOP permission check
	 * @param newModerator
	 */
	public boolean makeModerator(Location location, MagicPlayer adminOrMod, MagicPlayer newModerator);
	
}
