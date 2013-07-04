package com.mtg.web.interceptor;

import java.security.Principal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

/**
 * Add some commonly used variables to location views
 * 	1. moderator - boolean, true if logged in user is location moderator
 *  2. admin - boolean, true if user is admin, usually has same permissions as moderator
 * @author Mark
 */
@Component
public class LocationInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(LocationInterceptor.class);
	
	protected static final String MOD = "moderator";
	protected static final String ADMIN = "admin";
	
	@Resource
	private AccountService accounts;
	
	private Location getLocation(ModelAndView mav) {
		Map<String, Object> model = mav.getModel();
		
		//see CityController(etc)Impl.manage(..) and .newpost(..)
		if(null != model.get("type")) {
		    return (Location) model.get("location");
		}
		
		Meetup m = (Meetup) model.get(Location.MEETUP);
		if(null != m) return m;
		
		City ct = (City) model.get(Location.CITY);
		if(null != ct) return ct;
		
		Country cy = (Country) model.get(Location.COUNTRY);
		if(null != cy) return cy;
	
		return null;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
	
		if(null != mav) {
			try {
				Principal principal = request.getUserPrincipal();
				
				log.info("Intercepted location view. principal={}", principal);
				
				if(null == principal) {
					none(mav);
					return;
				}
				
				//check if admin
				Account account = accounts.findByUsername(principal.getName());
				if(null == account) {
					none(mav);
					return;
				}
				mav.addObject(ADMIN, Roles.hasRole(account, Roles.ROLE_ADMIN));
	
				//check if moderator
				Location loc = getLocation(mav);
				if(null == loc) {
					mav.addObject(MOD, false);
				} else {
					mav.addObject(MOD, loc.getModerators().contains(account.getPlayer()));
				}
			} finally {
				log.debug("Processing complete. moderator={}, admin={}", mav.getModel().get(MOD), mav.getModel().get(ADMIN));
			}
		}
	}	
	
	private void none(ModelAndView mav) {
		mav.addObject(MOD, false);
		mav.addObject(ADMIN, false);
	}
}
