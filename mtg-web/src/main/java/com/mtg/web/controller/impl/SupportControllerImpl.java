package com.mtg.web.controller.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.interactive.posts.services.FrontpageService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.SupportController;

@Component
public class SupportControllerImpl extends GenericController implements SupportController {

	@Resource
	private FrontpageService frontpage;
	
    @Override
    public ModelAndView welcome() {
        return mav("support/welcome");
    }
    
    @Override
    public ModelAndView frontpage() {
    	return mav("support/frontpage")
    			.addObject("posts", frontpage.getFrontpage().getPosts());
    }
    
	@Override
	public ModelAndView faq() {
		return mav("support/faq");
	}

	@Override
	public ModelAndView upcoming() {
		return mav("support/upcoming");
	}

	@Override
	public ModelAndView updates() {
		return mav("support/updates");
	}

}
