package com.mtg.commons.models.magic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;

@Entity
@Table(name="magicplayers")
public class MagicPlayer extends AbstractEntity {

    @OneToMany
    private List<Binder> binders;
    
    @ManyToOne
    private Country country;
    
    @ManyToMany(mappedBy = "players")
	private List<City> cities;
	
    @ManyToMany(mappedBy = "players")
	private List<Meetup> meetups;

	@Type(type="text")
    private String contact;
    
    public List<Binder> getBinders() {
        if(null == binders) {
            binders = new ArrayList<Binder>();
        }
        return binders;
    }

    public void setBinders(List<Binder> binders) {
        this.binders = binders;
    }

	public List<Meetup> getMeetups() {
		if(null == meetups) {
			meetups = new ArrayList<Meetup>();
		}
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public List<City> getCities() {
		if(null == cities) {
			cities = new ArrayList<City>();
		}
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
    
}
