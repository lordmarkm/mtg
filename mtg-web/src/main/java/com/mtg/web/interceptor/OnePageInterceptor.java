package com.mtg.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mtg.web.support.RequestParams;

/**
 * Ensure somehow that there is only one home page, and the background is what changes, like Facebook. We wan to keep the
 * chat footer constant. Also the header
 * @author Mark
 */
@Component
public class OnePageInterceptor extends HandlerInterceptorAdapter {
	
	static Logger log = LoggerFactory.getLogger(OnePageInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		String uri = request.getRequestURI();

		//ignore if ajax request
		if(null != request.getParameter(RequestParams.AJAX)) {
			return true;
		}
		
		//ignore resources
		for(String resource : new String[]{"/css/","/images/","/javascript/","/libs/"}) {
			if(uri.contains(resource)) return true;
		}
		
		//ignore posts like from forms
		if(RequestMethod.POST.toString().equals(request.getMethod())) {
			return true;
		}
			
		//ignore if request for front page
		if(uri != null && "/".equals(uri)) {
			return true;
		}
		
		log.info("Redirecting to index page with target uri={}", uri);
		response.sendRedirect("/?uri=" + uri);
		
		return false;
	}
}
