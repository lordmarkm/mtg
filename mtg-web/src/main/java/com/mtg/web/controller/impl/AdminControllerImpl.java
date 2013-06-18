package com.mtg.web.controller.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.Expansion;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.Rarities;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.CardService;
import com.mtg.commons.services.ExpansionService;
import com.mtg.parser.services.ListParser;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AdminController;
import com.mtg.web.dto.CardForm;
import com.mtg.web.dto.ExpansionForm;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.ParseCardsForm;

@Component
public class AdminControllerImpl extends GenericController implements AdminController {

    private static Logger log = LoggerFactory.getLogger(AdminControllerImpl.class);
    
    @Resource
    private ExpansionService exps;
    
    @Resource
    private CardService cards;
    
    @Resource
    private BinderService binders;
    
    @Resource
    private AccountService accounts;
    
    @Resource
    private ListParser parser;
    
    @Resource
    private Environment env;
    
    @Override
    public ModelAndView dashboard(Principal principal) {
        
        log.info("Admin dashboard requested. user={}", name(principal));
        
        return mav("admin/dashboard")
        		.addObject("env", env)
        		.addObject("usercount", accounts.count())
        		.addObject("cardcount", cards.count())
        		.addObject("bindercount", binders.count())
        		.addObject("expansions", exps.findAll());
    }

    @Override
    public JSON newExpansion(Principal principal, @Valid ExpansionForm form,
            BindingResult result) {
    	
        if(result.hasErrors()) {
            ObjectError error = result.getAllErrors().iterator().next();
            return JSON.error(error.getDefaultMessage());
        }

        Expansion newexp = exps.save(form.toExpansion());
        
        return JSON.ok().put("expansion", newexp);
    }

    @Override
    public JSON newcard(Principal principal, CardForm form, BindingResult result) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public JSON uploadExpansionSymbol(Principal principal, ImageForm form,
			@PathVariable String expcode, @PathVariable Rarities.Rarity rarity) throws IOException {

		Validate.notNull(form.getData());
		Image image = exps.saveSymbol(expcode, rarity, form.getData().getBytes());
		
		return JSON.ok().put("image", image);
	}

	@Override
	public JSON parseCards(Principal principal, @Valid ParseCardsForm form,
			BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
            ObjectError error = result.getAllErrors().iterator().next();
            return JSON.error(error.getDefaultMessage());
		}
		
		String expcode = form.getExpcode();
		String url = form.getUrl();
		Validate.notNull(expcode);
		Validate.notNull(url);
		
		Expansion exp = exps.findByCode(expcode);
		Validate.notNull(exp);
		
		List<Card> cards = parser.parseAll(form.getUrl());
		exps.setCards(exp, cards);
		
		return JSON.ok();
	}


}
