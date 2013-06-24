package com.mtg.web.controller.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.services.MeetupService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.MeetupController;

@Component
public class MeetupControllerImpl extends GenericController implements MeetupController {

    @Resource
    private MeetupService meetups;
    
    @Override
    public ModelAndView browse(@PathVariable String url) {
        return mav("meetup/meetup")
                .addObject("meetup", meetups.findByUrlFragment(url));
    }

}
