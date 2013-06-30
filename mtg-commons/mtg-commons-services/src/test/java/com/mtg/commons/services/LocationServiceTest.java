package com.mtg.commons.services;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class})
public class LocationServiceTest {

	@Resource
	private CountryService countries;
	
	@Resource
	private PlayerService players;
	
	@Test
	@Transactional
	public void testFindOccupied() {
		Country c = countries.save(Util.phils());
		assertEquals(1, countries.findAll().size());
		assertEquals(0, countries.findOccupied().size());
		
		MagicPlayer p = Util.cornboy();
		p.setCountry(c);
		players.save(p);
		
		c.getPlayers().add(Util.cornboy());
		
		assertEquals(1, players.findAll().size());
		
		Country s = countries.findOne(c.getId());

		assertEquals(1, s.getPlayers().size());
		assertEquals(1, countries.findOccupied().size());
	}
	
	
}
