package com.mtg.commons.models.locations;

import java.util.List;
import java.util.Set;

import com.mtg.commons.models.interactive.Postable;
import com.mtg.commons.models.magic.MagicPlayer;

public interface Location extends Postable {

	public static final String MEETUP = "meetup";
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	
	public enum Type {
		all,
		country,
		city,
		meetup
	}

	long getId();
	Location getParent();
	Set<MagicPlayer> getModerators();
	List<MagicPlayer> getPlayers();
	
}
