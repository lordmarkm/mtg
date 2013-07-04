package com.mtg.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.services.AccountService;

/**
 * Make available to every page the information required by the navbar, since it's always there
 * @author Mark
 */

@Component
public class NavbarInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = LoggerFactory.getLogger(NavbarInterceptor.class);
	
	@Resource
	private AccountService accounts;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {
	
		//TODO I THINK the only time navbar image is ever loaded is with  a OnePageInterceptor redirect. Not sure though
		if(mav != null && mav.getModel().get("target") != null) {
			Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(null != auth && auth instanceof User) {
				log.info("Injecting account for navbar.");
				User user = (User)auth;
				MagicPlayer mp = accounts.findByUsername(user.getUsername()).getPlayer();
				mav.addObject("navimage", mp.getImage());
			}
		}
		
	}
}
