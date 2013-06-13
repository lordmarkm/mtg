package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.mtg.web.controller.LiveController;
import com.mtg.web.dto.JSON;

@Component
public class LiveControllerImpl implements LiveController {

	private Map<String, DeferredResult<JSON>> waiting;
	
	@PostConstruct
	public void init() {
		waiting = new ConcurrentHashMap<String, DeferredResult<JSON>>();
	}
	
	@Override
	public DeferredResult<JSON> live(Principal principal) {
		DeferredResult<JSON> response = new DeferredResult<>();
		
		boolean respond = false;
		JSON json = JSON.ok();
		
		if(respond) {
			response.setResult(json);
		} else {
			waiting.put(principal.getName(), response);
		}
		return response;
	}

}
