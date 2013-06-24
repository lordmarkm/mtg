package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/m")
public interface MeetupController {

    @RequestMapping("/{url}")
    ModelAndView browse(String url);
    
}
