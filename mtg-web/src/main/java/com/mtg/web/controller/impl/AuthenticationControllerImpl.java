package com.mtg.web.controller.impl;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.security.services.AccountService;
import com.mtg.security.services.RegistrationService;
import com.mtg.web.controller.AuthenticationController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.AccountForm;
import com.mtg.web.dto.JSON;

@Component
public class AuthenticationControllerImpl extends GenericController implements AuthenticationController {

    static Logger log = LoggerFactory.getLogger(AuthenticationControllerImpl.class);
    
    @Resource
    private RegistrationService reg;
    
    @Resource
    private AccountService accounts;
    
    @Override
    public ModelAndView login() {
    	return login(null);
    }
    
    @Override
    public ModelAndView login(@PathVariable String message) {
        return mav("login")
        		.addObject("message", message);
    }

    @Override
    public ModelAndView register() {
        return mav("register")
                .addObject("form", new AccountForm());
    }

    //TODO put validation in a validator lol
    @Override
    public JSON register(@Valid AccountForm form, BindingResult result) {
        
        log.info("Registration request received. form={}", form);
        
        if(result.hasErrors()) {
            return JSON.error(result.getAllErrors().iterator().next().getDefaultMessage());
        }
        
        String username = form.getUsername();
        
        if(accounts.findByUsername(username) != null) {
            return JSON.error("Username " + username + " is already in use.");
        }
        
        String password = form.getPassword();
        String confirmPw = form.getConfirmpw();
        
        if(!password.equals(confirmPw)) {
            return JSON.error("Passwords must match.");
        }
        
        String email = form.getEmail();
        
        if(accounts.findByEmail(email) != null) {
            return JSON.error(email + " is already in use.");
        }
        
        reg.register(username, password, email);
        
        return JSON.ok();
    }

}
