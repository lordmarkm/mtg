package com.mtg.interactive.posts.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Frontpage;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.interactive.Postable;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.interactive.posts.services.FrontpageService;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.interactive.posts.services.PostServiceCustom;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

@Transactional
public class PostServiceCustomImpl extends AbstractEntityService implements PostServiceCustom {

	private static Logger log = LoggerFactory.getLogger(PostServiceCustomImpl.class);
	
	@Resource
	private PostService posts;
	
	@Resource
	private FrontpageService frontpage;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private CityService cities;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private AccountService accounts;
	
	@Override
	public Post post(Post post, PostParentType parentType, long parentId,
			MagicPlayer author) {
		
		Post saved = posts.save(post);
		saved.setUrlFragment(urlfragment(saved.getTitle()));
		
		//parent
		Postable parent = setParent(author, saved, parentType, parentId);
		parent.getPosts().add(saved);
		
		//author
		author.getPosts().add(saved);
		
		return saved;
	}
	
	protected Postable setParent(MagicPlayer author, Post post, PostParentType parentType, long parentId) {
		
		PostParent postParent = new PostParent();
		postParent.setParentType(parentType);
		postParent.setParentId(parentId);
		post.setParent(postParent);
		
		switch(parentType) {
		case frontpage:
			Frontpage page = frontpage.getFrontpage();
			Validate.notNull(page);
			Account account = accounts.findByPlayerId(author.getId());
			Validate.isTrue(Roles.hasRole(account, Roles.ROLE_ADMIN));
			postParent.setFrontpage(page);
			return page;
		case meetup:
			Meetup meetup = meetups.findOne(parentId);
			Validate.notNull(meetup);
			postParent.setMeetup(meetup);
			return meetup;
		case city:
			City city = cities.findOne(parentId);
			Validate.notNull(city);
			postParent.setCity(city);
			return city;
		case country:
			Country country = countries.findOne(parentId);
			Validate.notNull(country);
			postParent.setCountry(country);
			return country;
		default:
			throw new IllegalArgumentException("Unhandled post parent type: " + parentType);
		}
		
	}

	public void hide(Principal principal, Long id) throws AccessDeniedException {
		Validate.notNull(principal);
		Account requestor = accounts.findByUsername(principal.getName());
		Post post = posts.findOne(id);
		Validate.notNull(post);
		
		if(post.getAuthor().equals(requestor.getPlayer()) || Roles.hasRole(requestor, Roles.ROLE_ADMIN)) {
			post.setDeleted(true);
		} else {
			throw new AccessDeniedException("No! I am too sexy for you!");
		}
	}

	@Override
	public void edit(MagicPlayer player, Post post) {
		
		log.info("Editing post. player={}, post={}", player, post);
		
	}

	@Override
	public List<Post> findByFrontpageOrLocation(MagicPlayer player,
			Pageable pageRequest) {
		
		List<Long> cityIds = new ArrayList<Long>();
		List<Long> meetupIds = new ArrayList<Long>();
		
		//city Ids
		for(City city : player.getCities()) {
			cityIds.add(city.getId());
		}
		if(cityIds.isEmpty()) {
			cityIds.add(0L);
		}
		
		//meetup Ids
		for(Meetup meetup : player.getMeetups()) {
			meetupIds.add(meetup.getId());
		}
		if(meetupIds.isEmpty()) {
			meetupIds.add(0L);
		}
		
		//country Id, 0 if stateless
		Long countryId = player.getCountry() != null ? player.getCountry().getId() : 0L;

		return posts.findByFrontpageOrLocation(cityIds, meetupIds, countryId, pageRequest);
	}

}
