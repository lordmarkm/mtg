package com.mtg.cardlist.controller.impl;

import java.security.Principal;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.cardlist.controller.CardlistController;
import com.mtg.web.controller.GenericController;

@Component
public class CardlistControllerImpl extends GenericController implements CardlistController {

    @Override
    public ModelAndView cardlist(Principal principal) {
        return mav("cardlist/cardlist");
    }

}
