package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.CardForm;
import com.mtg.web.dto.ExpansionForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/admin")
public interface AdminController {

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView dashboard(Principal principal);
 
    @ResponseBody
    @RequestMapping(value = "/newexpansion", method = RequestMethod.POST)
    JSON newExpansion(Principal principal, ExpansionForm form, BindingResult result);
    
    @ResponseBody
    @RequestMapping(value = "/newcard", method = RequestMethod.POST)
    JSON newcard(Principal principal, CardForm form, BindingResult result);
    
}
