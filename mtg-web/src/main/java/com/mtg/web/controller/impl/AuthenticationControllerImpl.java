package com.mtg.web.controller.impl;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.security.services.RegistrationService;
import com.mtg.web.controller.AuthenticationController;
import com.mtg.web.dto.AccountForm;

@Component
public class AuthenticationControllerImpl extends GenericController implements AuthenticationController {

    static Logger log = LoggerFactory.getLogger(AuthenticationControllerImpl.class);
    
    @Resource
    private RegistrationService reg;
    
    @Override
    public ModelAndView login() {
    	return login(null);
    }
    
    @Override
    public ModelAndView login(@PathVariable String message) {
        return mav("login")
        		.addObject("error", message);
    }

    @Override
    public ModelAndView register() {
        return mav("register")
                .addObject("form", new AccountForm());
    }

    @Override
    public ModelAndView register(@Valid AccountForm form, BindingResult result) {
        
        log.info("Registration request received. form={}", form);
        
        if(result.hasErrors()) {
            return mav("register")
                    .addObject("form", form);
        }
        
        String username = form.getUsername();
        String password = form.getPassword();
        reg.register(username, password);
        
        return mav("redirect:/" + username);
    }

}
