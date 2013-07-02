package com.mtg.interactive.posts.services.aop;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

/**
 * Whenever somebody wants to modify a location (change picture, delete posts, etc), check access
 * 	1. Post - is a member
 *  2. Make moderator - is a moderator or admin
 *  3. Change locaion image/desc - is a moderator or admin
 * @author Mark
 *
 */

@Aspect
@Component
public class LocationModificationAspect {

	private static Logger log = LoggerFactory.getLogger(LocationModificationAspect.class);

	@Resource
	private AccountService accounts;

	@Before("@annotation(modOp) &&" +
			"args(location, modOrAdmin,..)")
	public void checkAccess(LocationModeratorOperation modOp, Location location, MagicPlayer modOrAdmin) throws AccessDeniedException {

		log.info("Location modification request. Checking moderator or admin access. location={}, requestor={}",
				location, modOrAdmin);
		
		if(location.getModerators().contains(modOrAdmin)) {
			log.info("Granted moderator access.");
			return;
		}

		Account account = accounts.findByPlayerId(modOrAdmin.getId());
		if(Roles.hasRole(account, Roles.ROLE_ADMIN)) {
			log.info("Granted admin access.");
			return;
		}

		throw new AccessDeniedException("No! I am too sexy for you.");
	}

}
