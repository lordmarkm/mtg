package com.mtg.interactive.posts.services.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used for LocationModificationAspect. Requires first 2 args to be MagicPlayer, Location
 * Annotated methods are accessible by Location Moderators or site admins
 * @author Mark
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface LocationModeratorOperation {
	//
}
