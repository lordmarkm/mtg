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

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.locations.Location;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

/**
 * Make sure this doesn't overlap with Location interceptor
 * 
 * Inject "admin=true" and "moderator=true" for posts and comments
 *  1. admin: site admin
 *  2. moderator: post>parent location moderator or comment>progenitor>parent location moderator
 * @author mbmartinez
 *
 */
@Component
public class PostOrCommentInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(PostOrCommentInterceptor.class);

    protected static final String MOD = "moderator";
    protected static final String ADMIN = "admin";

    @Resource
    private AccountService accounts;

    @Resource
    private CommentService comments;

    private Location getLocation(ModelAndView mav) {

        Map<String, Object> model = mav.getModel();

        Post post = (Post) model.get(Post.PREFERRED_MODEL_KEY);
        if(null != post) {
            return post.getParent().getLocationParent();
        }

        Comment comment = (Comment) model.get(Comment.PREFERRED_MODEL_KEY);
        if(null != comment) {
            Post progenitor = comments.getProgenitor(comment);
            if(null != progenitor) {
                return progenitor.getParent().getLocationParent();
            }
        }

        return null;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

        if(null == mav) {
            return;
        }

        try {
            Principal principal = request.getUserPrincipal();

            log.info("Intercepted post or comment view. principal={}", principal == null ? "Anonymous" : principal.getName());

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
            log.debug("Interception complete. moderator={}, admin={}", mav.getModel().get(MOD), mav.getModel().get(ADMIN));
        }
    }

    private void none(ModelAndView mav) {
        mav.addObject(MOD, false);
        mav.addObject(ADMIN, false);
    }

}
