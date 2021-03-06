package com.mtg.commons.models.magic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.Deck;
import com.mtg.commons.models.collections.Wanted;
import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;

/**
 * WARNING: If you want to allow editing MagicPlayer.name, please also see:
 *  1. UsernameInjectingInterceptor and the various places where mav.username is used
 * @author mbmartinez
 */

@Entity
@Table(name="magicplayers")
public class MagicPlayer extends AbstractEntity {

    @OneToMany
    private List<Binder> binders;
    
    @OneToMany
    private List<Deck> decks;
    
    @ManyToOne
    private Country country;
    
    @ManyToMany(mappedBy = "players")
	private List<City> cities;
	
    @ManyToMany(mappedBy = "players")
	private List<Meetup> meetups;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Wanted> wanted;
    
    @OneToMany(mappedBy="author")
    private List<Post> posts;
    
    @ManyToMany
    @JoinTable(name = "magicplayers_saved")
    @OrderColumn(name = "save_order")
    private List<Post> saved;
    
    @OneToMany(mappedBy="author")
    private List<Comment> comments;
    
	@Type(type="text")
    private String contact;
    
	public String toString() {
		return new ToStringCreator(this)
			.append("name", name)
			.toString();
	}
	
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

	public List<Wanted> getWanted() {
		return wanted;
	}

	public void setWanted(List<Wanted> wanted) {
		this.wanted = wanted;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getSaved() {
		if(null == saved) {
			saved = new ArrayList<Post>();
		}
		return saved;
	}

	public void setSaved(List<Post> saved) {
		this.saved = saved;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}


}
