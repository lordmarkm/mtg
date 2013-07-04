package com.mtg.interactive.posts.services;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.interactive.posts.services.aop.PostModificationAspect;
import com.mtg.interactive.posts.services.config.PostsServicesConfig;
import com.mtg.security.config.SecurityConfig;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class,
		SecurityConfig.class, PostsServicesConfig.class, PostsServicesTestConfig.class})
@Transactional
public class PostServiceTest {

	@Resource
	private PostService service;

	@Resource
	private PlayerService players;
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PostModificationAspect aspect;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;
	
	@Resource
	private CountryService countries;
	
	@Test
	public void config() {
		
	}
	
	public Account strangerWithPlayer() {
		MagicPlayer player = new MagicPlayer();
		player.setName("markm2");
		player.setDescription("just some guy");
		
		Account account = new Account();
		account.setUsername("markm2");
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_USER");
		return accounts.save(account);
	}
	
	public Account accountWithPlayer() {
		MagicPlayer player = new MagicPlayer();
		player.setName("markm");
		player.setDescription("just some guy");
//		MagicPlayer saved = players.save(player); !!!!APPARENTLY THIS SHOULDN'T BE SAVED INDIVIDUALLY
//		
//		AccountInfo info = new AccountInfo();
//		info.setAuthenticated(false);
//		info.setEmail("lordmarkm@gmail.com");
//		info.setJoined(DateTime.now());
//		info.setLastLogin(DateTime.now());
		
		Account account = new Account();
		account.setUsername("markm");
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_USER");
//		account.setInfo(info);
		return accounts.save(account);
	}
	
	public Post post(Account author) {
		PostParent parent = new PostParent();
		parent.setParentType(PostParentType.frontpage);
		
		Post post = new Post();
		post.setTitle("Hello");
		post.setText("world");
		post.setPostdate(DateTime.now());
		post.setAuthor(author.getPlayer());
		post.setParent(parent);
		return service.save(post);
	}
	
	public City dgte() {
		City dgte = new City();
		dgte.setName("Dumaguete");
		dgte.setDescription("TCOGP");
		return cities.save(dgte);
	}
	
	@Test
	public void author() {
		Account author = accountWithPlayer();
		Post post = post(author);
		service.edit(author.getPlayer(), post);
	}
	
	@Test
	public void admin() {
		Account author = accountWithPlayer();
		author.setAuthorities("ROLE_ADMIN");
		Post post = post(strangerWithPlayer());
		service.edit(author.getPlayer(), post);
	}
	
	@Test
	public void mod() {
		Account mod = accountWithPlayer();
		City dgte = dgte();
		dgte.getModerators().add(mod.getPlayer());
		
		PostParent parent = new PostParent();
		parent.setCity(dgte);
		parent.setParentId(dgte.getId());
		parent.setParentType(PostParentType.city);
		
		Post post = post(strangerWithPlayer());
		post.setParent(parent);
		
		service.edit(mod.getPlayer(), post);
	}
	
	private Meetup titan() {
		Meetup titan = new Meetup();
		titan.setName("titan");
		titan.setDescription("titan");
		return meetups.save(titan);
	}
	
	private Country ph() {
		Country ph = new Country();
		ph.setName("Philippines");
		ph.setDescription("ph");
		return countries.save(ph);
	}
	
	@Test
	public void testFrontpageOrLocation() {
		Account mod = accountWithPlayer();
		City dgte = dgte();
		Meetup titan = titan();
		Country ph = ph();
		
		MagicPlayer player = mod.getPlayer();
		player.getCities().add(dgte);
		player.getMeetups().add(titan);
		player.setCountry(ph);
		
		service.findByFrontpageOrLocation(player, new PageRequest(0, 10));
	}
}
