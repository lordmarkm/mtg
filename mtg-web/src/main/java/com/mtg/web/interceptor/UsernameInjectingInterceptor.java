package com.mtg.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Add user.username as "username" to all mavs
 * 
 * Used for:
 *  1. checking if post/comment author is the logged in user
 * @author mbmartinez
 */
@Component
public class UsernameInjectingInterceptor extends HandlerInterceptorAdapter {

    private final static String USERNAME = "username";

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        if(null != mav) {
            if(null != request.getUserPrincipal()) {
                mav.addObject(USERNAME, request.getUserPrincipal().getName());
            } else {
                mav.addObject(USERNAME, "");
            }
        }
    }
}
