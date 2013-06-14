package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.BinderService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountController;
import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.JSON;

@Component
public class AccountControllerImpl extends GenericController implements AccountController {

	private static Logger log = LoggerFactory.getLogger(AccountControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private BinderService binders;
	
	@Override
	public ModelAndView dashboard(Principal principal) {
		
		log.info("Dashboard requested. user={}", name(principal));
		
		Account account = accounts.findByUsername(principal.getName());
		return mav("account/dashboard")
				.addObject("account", account);
	}

    @Override
    public ModelAndView newbinder(Principal principal) {
        
        log.info("New binder form requested. user={}", name(principal));
        
        return mav("account/newbinder")
                .addObject("form", new BinderForm());
    }

    @Override
    public JSON newbinder(Principal principal, @Valid BinderForm form, BindingResult result) {

        log.info("Binder create request received. user={}, binder={}", name(principal), form);
        
        if(result.hasErrors()) {
            ObjectError error = result.getAllErrors().iterator().next();
            return JSON.error(error.getDefaultMessage());
        }
        
        MagicPlayer owner = accounts.findByUsername(name(principal)).getPlayer();
        Binder binder = binders.create(owner, form.toBinder());
        
        if(null == binder) {
            return JSON.error("Duplicate binder name not accepted");
        }
        
        return JSON.ok().message(owner.getName() + "/" + binder.getUrlFragment());
    }

}
