package com.mtg.commons.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.models.Expansion;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.Rarities.Rarity;

/**
 * @author Mark
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mtg.commons.services.config.CommonsServicesConfig.class})
public class ExpansionServiceTest {

	@Resource
	private ExpansionService exps;
	
	@Resource
	private ApplicationContext ctx;
	
	@Before
	public void reset() {
		exps.deleteAll();
	}
	
	private Expansion tsp() {
		Expansion exp = new Expansion();
		exp.setName("Time Spiral");
		exp.setCode("TSP");
		exp.setDescription("First set in the Time Spiral block");
		return exp;
	}
	
	@Test
	public void testSave() {
		Expansion tsp = tsp();
		Expansion saved = exps.save(tsp);
		assertNotNull(saved);
		assertTrue(0 != saved.getId());
	}
	
	@Test
	public void testSaveSymbol() throws IOException {
		Expansion tsp = exps.save(tsp());
		org.springframework.core.io.Resource image = ctx.getResource("classpath:tsp-symbol.png");
		Image saved = exps.saveSymbol(tsp.getCode(), Rarity.common, FileUtils.readFileToByteArray(image.getFile()));
		
		assertTrue(new File(saved.getPath()).exists());
		
		Expansion updated = exps.findByCode(tsp.getCode());
		assertNotNull(updated.getRarities().getCommon().getPath());
		assertTrue(new File(updated.getRarities().getCommon().getPath()).exists());
	}
}
