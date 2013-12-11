package com.mtg.cardlist.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("/cardlist")
public interface CardlistController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cardlist(Principal principal);

}
