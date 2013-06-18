package com.mtg.web.controller.impl;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private CityService cities;
	
	@Resource
	private CountryService countries;
	
	@Resource
	private MeetupService meetups;
	
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
	public ModelAndView addCity(Principal principal) {
		
		log.info("Add city request. user={}", name(principal));
		
		return mav("account/addcity")
				.addObject("cities", cities.findAll())
				.addObject("countries", countries.findAll())
				.addObject("form", new AddCityForm());
	}
	
	@Override
	public JSON addCity(Principal principal, @Valid AddCityForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return JSON.error(firstError(result));
		}
		
		MagicPlayer player = player(principal);
		
		Long id = form.getCityId();
		if(null != form.getCityId()) {
			//add existing city
			players.addCity(player, id);
		} else {
			//create new city then add
			//reject if name/description null
			String cityName = form.getName();
			String cityDesc = form.getDescription();
			
			if(null == cityName || cityName.trim().length() == 0) {
				return JSON.error("City must have a name.");
			} else if(null == cityDesc || cityDesc.trim().length() == 0) {
				return JSON.error("City must have a short description");
			}
			
			players.newCity(player, cityName, cityDesc, form.getCountryId());
		}
		
		return JSON.ok();
	}

	@Override
	public JSON removeCity(Principal principal, @PathVariable Long cityId) {
		log.info("Remove city request. user={}, city id={}", name(principal), cityId);
		
		MagicPlayer player = player(principal);
		players.removeCity(player, cityId);
		
		return JSON.ok();
	}

	@Override
	public ModelAndView addMeetup(Principal principal) {
		log.info("Add meetup request. user={}", name(principal));
		
		return mav("account/addmeetup")
				.addObject("cities", cities.findAll())
				.addObject("meetups", meetups.findAll())
				.addObject("form", new AddMeetupForm());
	}

	@Override
	public JSON addMeetup(Principal principal, AddMeetupForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return JSON.error(firstError(result));
		}
		
		MagicPlayer player = player(principal);
		
		Long id = form.getMeetupId();
		if(null != id) {
			//add existing meetup
			players.addMeetup(player, id);
		} else {
			//create new meetup then add
			//reject if name/description null
			String meetupName = form.getName();
			String meetupDesc = form.getDescription();
			
			if(null == meetupName || meetupName.trim().length() == 0) {
				return JSON.error("Meetup must have a name.");
			} else if(null == meetupDesc || meetupDesc.trim().length() == 0) {
				return JSON.error("Meetup must have a short description");
			}
			
			players.newMeetup(player, meetupName, meetupDesc, form.getCityId());
		}
		
		return JSON.ok();
	}

	@Override
	public JSON removeMeetup(Principal principal, @PathVariable Long meetupId) {
		log.info("Remove meetup request. user={}, meetup id={}", name(principal), meetupId);
		
		MagicPlayer player = player(principal);
		players.removeMeetup(player, meetupId);
		
		return JSON.ok();
	}

	@Override
	public JSON selectFlag(Principal principal, @PathVariable Long countryId) {
		log.info("Set country request. user={}, countryId={}", name(principal), countryId);
		
		Country country = countries.findOne(countryId);
		MagicPlayer player = player(principal);
		
		if(null == country && 0 == countryId) {
			players.removeFlag(player);
			return JSON.ok();
		}
		
		Validate.notNull(country);
		Validate.notNull(player);

		players.changeFlag(player, country);
		
		return JSON.ok().put("flag", country.getFlag());
	}
}
