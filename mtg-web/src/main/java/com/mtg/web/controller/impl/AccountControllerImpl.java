package com.mtg.web.controller.impl;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.Validate;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.CardService;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.ExpansionService;
import com.mtg.commons.services.MeetupService;
import com.mtg.commons.services.PageService;
import com.mtg.commons.services.PlayerService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.AccountController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.AddCityForm;
import com.mtg.web.dto.AddMeetupForm;
import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;

@Component
public class AccountControllerImpl extends GenericController implements AccountController {

	private static Logger log = LoggerFactory.getLogger(AccountControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private PlayerService players;
	
	@Resource
	private BinderService binders;
	
	@Resource
	private ExpansionService exps;
	
	@Resource
	private PageService pages;
	
	@Resource
	private CardService cards;
	
	@Resource
	private CountryService countries;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public ModelAndView dashboard(Principal principal) {
		
		log.info("Dashboard requested. user={}", name(principal));
		
		Account account = accounts.findByUsername(principal.getName());
		return mav("account/dashboard")
				.addObject("account", account)
				.addObject("countries", countries.findAll());
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
	public JSON uploadProfilePic(Principal principal, ImageForm form) throws IOException {

		log.info("Upload profile pic requested. user={}", name(principal));

		Image pic = accounts.saveProfilePic(principal.getName(), form.getData().getBytes());
		
		return JSON.ok().put("image", pic);
	}

	@Override
	public JSON deleteBinder(Principal principal, @PathVariable String urlFragment) {

		MagicPlayer player = player(principal);
		Binder binder = binders.findByOwnerAndUrlFragment(player.getName(), urlFragment);
		
		Validate.notNull(binder);
		binders.excise(binder);
		
		return JSON.ok();
	}

	@Override
	public JSON editContact(Principal principal, @RequestParam String contact) {

		log.info("Contact edit request. user={}, contact={}", name(principal), contact);
		
		players.setContact(player(principal).getName(), basic(contact));
		
		return JSON.ok();
	}

}
