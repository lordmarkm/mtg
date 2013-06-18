package com.mtg.commons.models.locations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="meetups")
public class Meetup extends AbstractEntity {

	@ManyToOne
	private City city;
	
	@ManyToMany
	private List<MagicPlayer> players;

	public List<MagicPlayer> getPlayers() {
		if(null == players) {
			players = new ArrayList<MagicPlayer>();
		}
		return players;
	}

	public void setPlayers(List<MagicPlayer> players) {
		this.players = players;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
