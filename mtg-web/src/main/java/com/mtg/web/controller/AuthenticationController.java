package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.AccountForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/auth")
public interface AuthenticationController {

    @RequestMapping("/login")
    public ModelAndView login();

    /**
     * @param message - badcredentials or regsuccess
     * @return
     */
    @RequestMapping("/login/{message}")
    public ModelAndView login(String message);
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register();

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSON register(AccountForm form, BindingResult result);

}
