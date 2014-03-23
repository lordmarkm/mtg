package com.mtg.cardlist.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.dtos.BundleDto;

@Controller
@RequestMapping("/cardlist")
public interface CardlistController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cardlist(Principal principal);

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView cardlist(Principal principal, String username);
    
    @ResponseBody
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public List<BundleDto> userCards(Principal principal, String username);

}
