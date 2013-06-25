package com.mtg.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping({"/cards","/c"})
public interface CardController {

    @RequestMapping("/browse")
    ModelAndView browse(Principal principal);
    
	@RequestMapping("/{id}")
	ModelAndView card(Principal principal, Long id) throws MalformedURLException, IOException;
	
	@ResponseBody
	@RequestMapping("/datatables")
	JSON dataTable(Principal principal, WebRequest request);
	
	@ResponseBody
	@RequestMapping("/datatables/{expcode}")
	JSON dataTable(Principal principal, String expcode, WebRequest request);
	
	@RequestMapping("/multi/{idstring}")
	ModelAndView multipleCards(Principal principal, String idstring);
	
	@RequestMapping("/find/{id}")
	ModelAndView findCardInBinders(Principal principal, Long id);
}
