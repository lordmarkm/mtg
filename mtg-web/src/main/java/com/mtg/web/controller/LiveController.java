package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/live")
public interface LiveController {
	
	@RequestMapping(method = RequestMethod.POST)
	DeferredResult<JSON> live(Principal principal);
	
}
