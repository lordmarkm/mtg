package com.mtg.commons.models.locations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="cities")
public class City extends AbstractEntity implements Location {

	@ManyToOne
	private Country country;

	@ManyToMany
	@JoinTable(name = "cities_moderators")
	private Set<MagicPlayer> moderators;
	
	@ManyToMany
	private List<MagicPlayer> players;

	@OneToMany(mappedBy="city")
	private List<Meetup> meetups;
	
	@OneToMany
	private List<Post> posts;
	
	@Column
	private boolean banned = false;
	
	public City() {
		//
	}
	
	public City(String name, String desc) {
		super(name, desc);
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("name", name)
			.append("id", id)
			.toString();
			
	}
	
	@Override
	public Location getParent() {
		return country;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Meetup> getMeetups() {
		if(null == meetups) {
			this.meetups = new ArrayList<Meetup>();
		}
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<MagicPlayer> getModerators() {
		if(null == moderators) {
			moderators = new HashSet<MagicPlayer>();
		}
		return moderators;
	}

	public void setModerators(Set<MagicPlayer> moderators) {
		this.moderators = moderators;
	}
	
}
