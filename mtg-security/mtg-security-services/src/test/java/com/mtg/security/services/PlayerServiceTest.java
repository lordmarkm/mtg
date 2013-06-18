package com.mtg.security.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.security.config.PersistenceConfig;
import com.mtg.security.models.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsServicesConfig.class, PersistenceConfig.class})
public class PlayerServiceTest {
	
	private static Logger log = LoggerFactory.getLogger(PlayerServiceTest.class);
	
	@Resource
	private CityService cities;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private AccountService accounts;
	
	@Test
	public void testNewCity() {
		Account a = Util.account();
		Account s = accounts.save(a);
		
		MagicPlayer sp = players.newCity(s.getPlayer(), "Dumaguete", "City of gentle ppl", 0L);
		log.info("Cities saved: {}", sp.getCities().getClass());
		assertTrue(cities.count() > 0);
	}
	
	@Test
	@Transactional
	public void testAddCity() {
		Account a = Util.account();
		Account s = accounts.save(a);
		
		MagicPlayer sp = s.getPlayer();
		
		City c = cities.save(Util.city());
		
		players.addCity(sp, c.getId());
		
		MagicPlayer np = players.findOne(sp.getId());
		assertTrue(np.getCities().size() == 1);
		
		City nc = cities.findOne(c.getId());
		assertTrue(nc.getPlayers().size() == 1);
	}
	
	@Test
	public void testCompare() {
		City c = Util.city();
		
		City s = cities.saveAndFlush(c);
		List<City> l = new ArrayList<City>();
		l.add(s);
		
		assertTrue(!c.equals(s));
		assertTrue(!l.contains(c));
		
		c.setId(s.getId());
		assertTrue(c.equals(s));
		assertTrue(l.contains(c));
	}
	
	@Test
	@Transactional
	public void testRemoveCity() {
		Account a = Util.account();
		Account s = accounts.save(a);
		
		MagicPlayer sp = s.getPlayer();
		
		City c = cities.save(Util.city());
		
		players.addCity(sp, c.getId());
		
		MagicPlayer np = players.findOne(sp.getId());
		assertTrue(np.getCities().size() == 1);
		
		City nc = cities.findOne(c.getId());
		assertTrue(nc.getPlayers().size() == 1);
		
		players.removeCity(np, nc.getId());
		MagicPlayer npp = players.findOne(np.getId());
		
		assertTrue(npp.getCities().size() == 0);
		assertTrue(null == cities.findOne(nc.getId()));
		assertTrue(cities.count() == 0);
	}
}
