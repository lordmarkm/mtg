package com.mtg.commons.models.locations;

import java.util.List;

import com.mtg.commons.models.interactive.Postable;
import com.mtg.commons.models.magic.MagicPlayer;

public interface Location extends Postable {

	public enum Type {
		all,
		country,
		city,
		meetup
	}

	long getId();
	Location getParent();
	List<MagicPlayer> getPlayers();
	
}
