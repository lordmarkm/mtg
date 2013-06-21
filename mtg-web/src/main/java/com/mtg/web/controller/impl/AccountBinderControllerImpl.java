package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.CardService;
import com.mtg.commons.services.ExpansionService;
import com.mtg.commons.services.PageService;
import com.mtg.commons.services.PlayerService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountBinderController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.JSON;

@Component
public class AccountBinderControllerImpl extends GenericController implements AccountBinderController {
	
	private static Logger log = LoggerFactory.getLogger(AccountBinderControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private BinderService binders;
	
	@Resource
	private PageService pages;
	
	@Resource
	private ExpansionService exps;
	
	@Resource
	private CardService cards;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
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
            return JSON.error(firstError(result));
        }
        
        MagicPlayer owner = accounts.findByUsername(name(principal)).getPlayer();
        Binder binder = binders.create(owner, form.toBinder());
        
        if(null == binder) {
            return JSON.error("Duplicate binder name not accepted");
        }
        
        return JSON.ok().message(binder.getUrlFragment());
    }

	@Override
	public ModelAndView editbinder(Principal principal, @PathVariable String urlFragment) {
		log.info("Binder edit request. user={}, binder={}", name(principal), urlFragment);
		MagicPlayer player = accounts.findByUsername(name(principal)).getPlayer();
		Binder binder = binders.findByOwnerAndUrlFragment(player.getName(), urlFragment);
		
		return mav("account/editbinder")
				.addObject("binder", binder)
				.addObject("exps", exps.findAll());

	}
    
	@Override
	public ModelAndView editbinderPage(Principal principal, @PathVariable String urlFragment,
			@PathVariable Integer page) {

		MagicPlayer player = accounts.findByUsername(name(principal)).getPlayer();
		Binder binder = binders.findByOwnerAndUrlFragment(player.getName(), urlFragment);
		BinderPage binderPage = pages.findPage(binder.getId(), page);
		
		return mav("account/binderpage").addObject("page", binderPage);
	}
	
	@Override
	public JSON addCard(Principal principal, @PathVariable String urlFragment,
			@PathVariable Integer page, @PathVariable Long cardId) {
		
		log.info("Add card request. user={}, binder={}, card={}", name(principal), urlFragment, cardId);
		
		MagicPlayer player = accounts.findByUsername(name(principal)).getPlayer();
		Binder binder = binders.findByOwnerAndUrlFragment(player.getName(), urlFragment);
		Card card = cards.findOne(cardId);
		
		binders.addCard(binder, page, card);

		return JSON.ok();
	}
	
	@Override
	public JSON deleteBinder(Principal principal, @PathVariable String urlFragment) {

		MagicPlayer player = player(principal);
		Binder binder = binders.findByOwnerAndUrlFragment(player.getName(), urlFragment);
		
		Validate.notNull(binder);
		binders.excise(binder);
		
		return JSON.ok();
	}
}
