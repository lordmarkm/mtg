package com.mtg.commons.models.locations;

import java.util.List;

import com.mtg.commons.models.magic.MagicPlayer;

public interface Location {

	public enum Type {
		all,
		country,
		city,
		meetup
	}

	Location getParent();
	List<MagicPlayer> getPlayers();
	
}
