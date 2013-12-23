package com.mtg.events.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Single page application for managing events? I dunno lol
 * @author mbmartinez
 */
@Controller
@RequestMapping("/event")
public interface EventController {

    @RequestMapping("/manage")
    public ModelAndView manageEvents(Principal principal);

}
