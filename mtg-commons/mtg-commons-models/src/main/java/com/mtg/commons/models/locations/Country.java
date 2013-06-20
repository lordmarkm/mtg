package com.mtg.commons.models.locations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.magic.MagicPlayer;

/**
 * http://www.customicondesign.com/free-icons/flag-icon-set/all-in-one-country-flag-icon-set/
 */

@Entity
@Table(name="countries")
public class Country extends AbstractEntity implements Location {

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image flag;
	
	@OneToMany(mappedBy="country")
	private List<City> cities;

	@OneToMany(mappedBy="country")
	private List<MagicPlayer> players;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("name", name)
			.append("cities", cities)
			.toString();
	}
	
	@Override
	public Location getParent() {
		return null;
	}
	
	public Image getFlag() {
		return flag;
	}

	public void setFlag(Image flag) {
		this.flag = flag;
	}

	public List<City> getCities() {
		if(null == cities) {
			this.cities = new ArrayList<City>();
		}
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public List<MagicPlayer> getPlayers() {
		if(null == players) {
			this.players = new ArrayList<MagicPlayer>();
		}
		return players;
	}

	public void setPlayers(List<MagicPlayer> players) {
		this.players = players;
	}
	
	
}
