package com.mtg.commons.services;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class})
public class MeetupServiceTest {

    private Logger log = LoggerFactory.getLogger(MeetupServiceTest.class);
    
    @Resource
    private MeetupService meetups;
    
    @Resource
    private PlayerService players;
    
    @Test
    public void config() {
        
    }
    
    @Test
    public void testSave() {
        Meetup m = meetups.save(Util.titan());
        MagicPlayer p = players.save(Util.cornboy());
        m.getModerators().add(p);
        m.getPlayers().add(p);
        meetups.save(m);
        log.debug("Saved: {}", m);
    }
}
