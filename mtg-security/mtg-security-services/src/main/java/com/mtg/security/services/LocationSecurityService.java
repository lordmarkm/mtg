package com.mtg.security.services;

import java.security.Principal;

import com.mtg.commons.models.locations.Location;

public interface LocationSecurityService {

	public void ensureModOrAdmin(Principal principal, Location location);
	
}
