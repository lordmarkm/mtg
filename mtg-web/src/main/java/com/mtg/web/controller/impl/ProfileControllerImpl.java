package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.services.BinderService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.ProfileController;

@Component
public class ProfileControllerImpl extends GenericController implements ProfileController {

	private static Logger log = LoggerFactory.getLogger(ProfileControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private BinderService binders;
	
	@Override
	public ModelAndView ownprofile(Principal principal) {
		return profile(principal, principal.getName());
	}
	
	@Override
	public ModelAndView profile(Principal principal, @PathVariable String username) {
		
		log.info("Profile page requested. username={}, requestor={}", username, name(principal));
		
		Account user = accounts.findByUsername(username);
		
		return mav("profile/profile")
				.addObject("user", user);
	}

    @Override
    public ModelAndView binder(Principal principal, @PathVariable String username,
            @PathVariable String bindername) {
        
        Validate.notEmpty(username);
        Validate.notEmpty(bindername);
        
        log.info("Binder view requested. user={}, owner={}, binder={}", name(principal), username, bindername);
        
        Binder binder = binders.findByOwnerAndUrlFragment(username, bindername);
        
        Boolean hascards = false;
        if(null != binder) {
	        for(BinderPage page : binder.getPages()) {
	        	if(page.getBundles().size() > 0) {
	        		hascards = true;
	        		break;
	        	}
	        }
        }
        
        return mav("profile/binder")
        		.addObject("hascards", hascards)
                .addObject("binder", binder);
    }

}
