package com.mtg.interactive.posts.services.aop;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent;
import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

/**
 * When somebody tries to edit or "delete" a post, make sure that that person is either the poster, an admin,
 * or a moderator of the post's parent
 * @author Mark
 */
@Aspect
@Component
public class PostModificationAspect {

	@Resource
	private AccountService accounts;
	
	private static Logger log = LoggerFactory.getLogger(PostModificationAspect.class);
	
	@Pointcut("target(com.mtg.interactive.posts.services.impl.PostServiceCustomImpl) && execution(* edit(..))")
	public void edit() {}
	
	@Pointcut("target(com.mtg.interactive.posts.services.impl.PostServiceCustomImpl) && execution(* hide(..))")
	public void hide() {}
	
	@Before("(edit() || hide()) &&" +
	        "args(player, post)")
	public void checkAccess(MagicPlayer player, Post post) {
		Validate.notNull(player);
		Validate.notNull(post);
		
		log.info("Checking access to modify post. player={}, post={}", player.getName(), post);
		
		if(player.equals(post.getAuthor())) {
			log.info("Granting author access.");
			return;
		}
		
		PostParent parent = post.getParent();
		Location loc = null;
		switch(parent.getParentType()) {
		case city:
			loc = parent.getCity();
			break;
		case meetup:
			loc = parent.getMeetup();
			break;
		case country:
			loc = parent.getCountry();
			break;
		default:
			loc = null;
		}
		
		if (null != loc && loc.getModerators().contains(player)) {
			log.info("Granting moderator access.");
		} else if (Roles.hasRole(accounts.findByPlayerId(player.getId()), Roles.ROLE_ADMIN)) {
			log.info("Granting admin access.");
		} else {
			throw new AccessDeniedException("No! I am too sexy for you.");
		}
	}
	
}
