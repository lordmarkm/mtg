package com.mtg.commons.services;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.models.Card;
import com.mtg.commons.services.config.CommonsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsServicesConfig.class})
public class CardServiceTest {

	@Resource
	private CardService service;
	
	@Before
	public void reset() {
		//service.deleteAll();
	}
	
	private Card wog() {
		Card card = new Card();
		card.setName("Wrath of God");
		card.setDescription("Destroy all creatures. They can't be regenerated");
		return card;
	}
	
	@Test
	public void testSave() {
		Card wog = wog();
		service.save(wog);
		assertEquals(1, ((List<Card>)service.findAll()).size());
	}
	
	@Test
	public void testfind() {
		PageRequest request = new PageRequest(0,10);
		service.findByExpCode("TSP", request);
	}
}
