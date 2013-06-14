package com.mtg.web.controller.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.controller.AccountController;
import com.mtg.web.controller.AdminController;
import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.CardForm;
import com.mtg.web.dto.ExpansionForm;
import com.mtg.web.dto.JSON;

@Component
public class AdminControllerImpl extends GenericController implements AdminController {

    private static Logger log = LoggerFactory.getLogger(AdminControllerImpl.class);
    
    @Override
    public ModelAndView dashboard(Principal principal) {
        
        log.info("Admin dashboard requested. user={}", name(principal));
        
        return mav("admin/dashboard");
    }

    @Override
    public JSON newExpansion(Principal principal, ExpansionForm form,
            BindingResult result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSON newcard(Principal principal, CardForm form, BindingResult result) {
        // TODO Auto-generated method stub
        return null;
    }


}
