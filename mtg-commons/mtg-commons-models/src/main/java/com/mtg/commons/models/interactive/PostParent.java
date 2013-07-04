package com.mtg.commons.models.interactive;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;

@Embeddable
public class PostParent {

	public enum PostParentType {
		frontpage,
		country,
		city,
		meetup
	}
	
	@Column
	@Enumerated(EnumType.STRING)
	private PostParentType parentType;
	
	@Column
	private long parentId;
	
	@ManyToOne
	private Frontpage frontpage;
	
	@ManyToOne
	private Country country;
	
	@ManyToOne
	private City city;
	
	@ManyToOne
	private Meetup meetup;

	public Postable getParent() {
		if(null != frontpage) return frontpage;
		if(null != country) return country;
		if(null != city) return city;
		if(null != meetup) return meetup;
		return null; //frontpage
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Meetup getMeetup() {
		return meetup;
	}

	public void setMeetup(Meetup meetup) {
		this.meetup = meetup;
	}

	public Frontpage getFrontpage() {
		return frontpage;
	}

	public void setFrontpage(Frontpage frontpage) {
		this.frontpage = frontpage;
	}

	public PostParentType getParentType() {
		return parentType;
	}

	public void setParentType(PostParentType parentType) {
		this.parentType = parentType;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
}
