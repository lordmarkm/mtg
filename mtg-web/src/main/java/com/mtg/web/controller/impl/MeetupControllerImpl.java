package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.services.MeetupService;
import com.mtg.security.services.LocationSecurityService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.MeetupController;

@Component
public class MeetupControllerImpl extends GenericController implements MeetupController {

	private static Logger log = LoggerFactory.getLogger(MeetupControllerImpl.class);
	
    @Resource
    private MeetupService meetups;
    
	@Resource
	private LocationSecurityService locations;
    
    @Override
    public ModelAndView browse(@PathVariable String url) {
        return mav("meetup/meetup-posts")
                .addObject("meetup", meetups.findByUrlFragment(url));
    }

	@Override
	public ModelAndView newpost(Principal principal, @PathVariable String urlFragment) {
		log.info("New post form requested. user={}, meetup={}", name(principal), urlFragment);
		
		Meetup meetup = meetups.findByUrlFragment(urlFragment);
		Validate.notNull(meetup);
		
		return mav("location/post-form")
				.addObject("location", meetup)
				.addObject("type", PostParentType.meetup);
	}
    
	@Override
	public ModelAndView players(Principal principal, @PathVariable String urlFragment) {
		log.info("Meetup players page requested. user={}, meetup={}", name(principal), urlFragment);
		
		Meetup meetup = meetups.findByUrlFragment(urlFragment);
		return mav("meetup/meetup-players")
				.addObject("meetup", meetup);
	}

	@Override
	public ModelAndView manage(Principal principal, @PathVariable String urlFragment) {
		
		log.info("Meetup management page requested. user={}, name={}", name(principal), urlFragment);

		Meetup meetup  = meetups.findByUrlFragment(urlFragment);
		locations.ensureModOrAdmin(principal, meetup);
		
		return mav("location/manage")
				.addObject("location", meetup)
				.addObject("type", PostParentType.meetup);
	}
	
}
