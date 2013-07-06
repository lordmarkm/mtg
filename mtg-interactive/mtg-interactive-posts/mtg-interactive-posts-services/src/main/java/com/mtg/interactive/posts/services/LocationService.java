package com.mtg.interactive.posts.services;

import com.mtg.commons.models.locations.Location.Type;
import com.mtg.security.models.Account;

public interface LocationService {

	/**
	 * @param type - location type
	 * @oaram id - location id
	 * @param playerId - new mod id
	 */
	void makemod(Account requestor, Type type, Long id, Long playerId);
	
}
