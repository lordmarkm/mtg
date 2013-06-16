package com.mtg.web.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Rarities;
import com.mtg.web.dto.CardForm;
import com.mtg.web.dto.ExpansionForm;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.ParseCardsForm;

@Controller
@RequestMapping("/admin")
public interface AdminController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    ModelAndView dashboard(Principal principal);
 
    @ResponseBody
    @RequestMapping(value = "/newexpansion", method = RequestMethod.POST)
    JSON newExpansion(Principal principal, ExpansionForm form, BindingResult result);
    
    @ResponseBody
    @RequestMapping(value = "/newcard", method = RequestMethod.POST)
    JSON newcard(Principal principal, CardForm form, BindingResult result);
    
    @ResponseBody
    @RequestMapping(value = "/upload/expsymbol/{expcode}/{rarity}")
    JSON uploadExpansionSymbol(Principal principal, ImageForm form, String expcode, Rarities.Rarity rarity) throws IOException;
    
    @ResponseBody
    @RequestMapping(value = "/parsecards")
    JSON parseCards(Principal principal, ParseCardsForm form, BindingResult result) throws IOException;
}
