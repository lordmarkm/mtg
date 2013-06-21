package com.mtg.commons.services;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;


public class Util {

	public static Meetup titan() {
		Meetup m = new Meetup();
		m.setName("Titan");
		m.setDescription("Small place near Ortigas Center");
		return m;
	}
	
	public static Country phils() {
		Country c = new Country();
		c.setName("Philippines");
		c.setDescription("The Pearl of the Orient");
		return c;
	}
	
	public static City dgte() {
		City c = new City();
		c.setName("Dumaguete");
		c.setDescription("The City of Gentle People");
		return c;
	}
	
    public static Card wog() {
        Card card = new Card();
        card.setName("Wrath of God");
        card.setDescription("Destroy all creatures. They can't be regenerated");
        return card;
    }

	public static MagicPlayer cornboy() {
		MagicPlayer m = new MagicPlayer();
		m.setName("Cornboy");
		m.setDescription("From Las Vegas");
		return m;
	}
	
	public static  Binder white() {
    	Binder binder = new Binder();
    	binder.setName("Whites");
    	binder.setDescription("White cards");
    	return binder;
    }
    
}
