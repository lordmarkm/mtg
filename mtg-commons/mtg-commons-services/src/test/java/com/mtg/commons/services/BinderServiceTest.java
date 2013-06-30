package com.mtg.commons.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class})
public class BinderServiceTest {

    @Resource
    private BinderService binders;
    
    @Resource
    private CardService cards;
    
    @Resource
    private BundleService bundles;
    
    @Resource
    private PageService pages;
    
    @Resource
    private PlayerService players;
    
    @Resource
    private DataSource ds;
    
    @Resource
    private CountryService countries;
    
    @Resource
    private CityService cities;
    
    @Resource
    private MeetupService meetups;
    
    @Before
    public void init() {
    	//cards.deleteAll();
    	//binders.deleteAll();
    }
    
    @After
    public void tearDown() {
        try {
            //clearDatabase();
        } catch (Exception e) {
        }
    }
    
    //@Test
    public void clear() throws Exception {
        //clearDatabase();
    }


    public void clearDatabase() throws Exception {
      Connection connection = null;
      try {
        connection = ds.getConnection();
        try {
          Statement stmt = connection.createStatement();
          try {
            stmt.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
            connection.commit();
          } finally {
            stmt.close();
          }
        } catch (SQLException e) {
            connection.rollback();
            throw new Exception(e);
        }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    @Test
    public void testConfigLoaded() {
        assertNotNull(binders);
    }
    
    //@Test
    @Transactional
    public void testAddCard() {
    	
    	assertEquals(0, bundles.count());
    	
    	int PAGE = 2;
    	
    	Card card = Util.wog();
    	Binder binder = Util.white();
    	
    	Card pCard = cards.save(card);
    	Binder pBind = binders.save(binder);
    	
    	binders.addCard(pBind, PAGE, pCard);
    	
    	//check that a bundle was saved in a binder > page
    	Binder sBind = binders.findOne(pBind.getId());
    	assertTrue(sBind.getPages().size() == Binder.PAGES);
    	
    	int currentPage = 0;
    	for(BinderPage page : sBind.getPages()) {
    		currentPage = page.getPageNumber();
    		if(page.getBundles().size() > 0) break;
    	}
    	assertEquals(PAGE, currentPage);
    	
    	//check that the card knows it's in a bundle that's on the right page
    	Card sCard = cards.findOne(pCard.getId());
    	//assertEquals(1, sCard.getBundles().size());
    	//assertEquals(PAGE, sCard.getBundles().iterator().next().getPage().getPageNumber());
    }
    
    //@Test
    @Transactional
    public void testDelete() {
    	//assertTrue(bundles.count() == 0);
    	
    	int PAGE = 2;
    	
    	Card card = Util.wog();
    	Binder binder = Util.white();
    	
    	Card pCard = cards.save(card);
    	Binder pBind = binders.save(binder);
    	
    	binders.addCard(pBind, PAGE, pCard);
    	Binder sBind = binders.findOne(pBind.getId());
    	for(Iterator<BinderPage> i = sBind.getPages().iterator(); i.hasNext();) {
    		BinderPage p = i.next();
    		for(Bundle b : p.getBundles()) {
    			//b.getCard().getBundles().remove(b);
    		}
    		i.remove();
    		pages.delete(p);
    	}
    	
    	for(BinderPage p : sBind.getPages()) {
    		bundles.deleteInBatch(p.getBundles());
    	}
    	//pages.deleteInBatch(sBind.getPages());
    	binders.delete(pBind.getId());
    }
    
    @Test
    @Transactional
    public void testFindByCountry() {
    	Country ph = countries.save(Util.phils());
    	MagicPlayer p = players.save(Util.cornboy());
    	Binder b = binders.save(Util.white());
    	
    	ph.getPlayers().add(p);
    	p.setCountry(ph);

    	p.getBinders().add(b);
    	b.setOwner(p);
    	
    	List<Binder> phBinds = binders.findByCountry(ph.getId());
    	assertEquals(1, phBinds.size());
    }
    
    @Test
    @Transactional
    public void testFindByCity() {
    	City c = cities.save(Util.dgte());
    	MagicPlayer p = players.save(Util.cornboy());
    	Binder b = binders.save(Util.white());
    	
    	c.getPlayers().add(p);
    	p.getCities().add(c);
    	
    	p.getBinders().add(b);
    	b.setOwner(p);
    	
    	List<Binder> dgteBinds = binders.findByCity(c.getId());
    	assertEquals(1, dgteBinds.size());
    }
    
    @Test
    @Transactional
    public void testFindBymeetup() {
    	Meetup m = meetups.save(Util.titan());
    	MagicPlayer p = players.save(Util.cornboy());
    	Binder b = binders.save(Util.white());
    	
    	m.getPlayers().add(p);
    	p.getMeetups().add(m);
    	
    	p.getBinders().add(b);
    	b.setOwner(p);
    	
    	List<Binder> titanBinds = binders.findByMeetup(m.getId());
    	assertEquals(1, titanBinds.size());
    }
}
