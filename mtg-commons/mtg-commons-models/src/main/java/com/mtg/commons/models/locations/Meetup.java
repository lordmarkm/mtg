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

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="meetups")
public class Meetup extends AbstractEntity implements Location {

	@ManyToOne
	private City city;
	
	@ManyToMany
	@JoinTable(name = "meetups_moderators")
	private Set<MagicPlayer> moderators;
	
	@ManyToMany
	private List<MagicPlayer> players;

	@OneToMany
	private List<Post> posts;
	
	@Column
	private boolean banned = false;
	
	@Override
	public List<MagicPlayer> getPlayers() {
		if(null == players) {
			players = new ArrayList<MagicPlayer>();
		}
		return players;
	}

	@Override
	public Location getParent() {
		return city;
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
