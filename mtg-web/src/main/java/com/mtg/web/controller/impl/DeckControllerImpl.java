package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.mtg.commons.models.collections.Deck;
import com.mtg.commons.services.DeckService;
import com.mtg.web.controller.DeckController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.DtoMaker;
import com.mtg.web.dto.JSON;

@Component
public class DeckControllerImpl extends GenericController implements DeckController {

    private static Logger log = LoggerFactory.getLogger(DeckControllerImpl.class);
    
    @Resource
    private DeckService decks;

    @Override
    public JSON json(Principal principal, @PathVariable Long id) {
        log.info("JSON deck request. user={}, deck={}", name(principal), id);
        Deck deck = decks.findOne(id);
        
        return JSON.ok()
                    .put("deck", DtoMaker.transform(deck));
    }

}
