package com.mtg.commons.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

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
import com.mtg.commons.services.config.CommonsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsServicesConfig.class})
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
    private DataSource ds;
    
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
    	
    	Card card = wog();
    	Binder binder = white();
    	
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
    	
    	Card card = wog();
    	Binder binder = white();
    	
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
    
    private Binder white() {
    	Binder binder = new Binder();
    	binder.setName("Whites");
    	binder.setDescription("White cards");
    	return binder;
    }
    
	private Card wog() {
		Card card = new Card();
		card.setName("Wrath of God");
		card.setDescription("Destroy all creatures. They can't be regenerated");
		return card;
	}
    
}
