package com.mtg.interactive.posts.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.aop.LocationModeratorOperation;

@Service
public class InteractiveLocationServiceImpl implements InteractiveLocationService {

	private static Logger log = LoggerFactory.getLogger(InteractiveLocationServiceImpl.class);
	
	@Override
	@LocationModeratorOperation
	public boolean makeModerator(Location location, MagicPlayer adminOrMod, MagicPlayer newModerator) {
		log.info("Moderator promotion request. requestor={}, new mod = {}, location={}",
				adminOrMod, newModerator, location);
		
		location.getModerators().add(newModerator);
		return true;
	}

}

