package com.mtg.web.controller.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.services.CardService;
import com.mtg.web.controller.CardController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.JSON;
import com.mtg.web.support.DataTables;

@Component
public class CardControllerImpl extends GenericController implements CardController {

	private static Logger log = LoggerFactory.getLogger(CardControllerImpl.class);
	
	@Resource
	private CardService cardserv;
	
	@Override
	public ModelAndView card(Principal principal, @PathVariable Long id) throws MalformedURLException, IOException {
		Card card = cardserv.findOne(id);
		
		log.info("Card view request. user={}, id={}, name={}", name(principal), id, card != null ? card.getName() : "Not found");
		return mav("card").addObject("card", card);
	}

    @Override
    public ModelAndView multipleCards(Principal principal, @PathVariable String idstring) {

        log.info("View multiple card request. user={}, ids={}", name(principal), idstring);
        
        String[] ids = idstring.split(",");
        List<Card> cards = new ArrayList<Card>();
        for(String id : ids) {
            
            Long longId = null;
            try {
                longId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                continue;
            }
            
            Card found = cardserv.findOne(longId);
            if(null != found) cards.add(found);

        }
        
        return mav("card/multiple").addObject("cards", cards);
    }
	
	@Override
	public JSON dataTable(Principal principal, @PathVariable String expcode, @RequestParam("sEcho") int echo,
			@RequestParam("iDisplayStart") int start, @RequestParam("iDisplayLength") int length) {
		
		log.debug("Datatable request for cards. user={}, expcode={}, start={}, length={}",
				name(principal), expcode, start, length);

		Pageable request = new PageRequest(start/length, length);
		Page<Card> page = cardserv.findByExpCode(expcode, request);

		return JSON.ok()
				.put(DataTables.ECHO, echo)
				.put(DataTables.TOTAL_RECORDS, page.getTotalElements())
				.put(DataTables.TOTAL_DISPLAY_RECORDS, page.getTotalElements())
				.put(DataTables.DATA, cardserv.toDataTableResponse(page.getContent()));
	}


    @Override
    public ModelAndView findCardInBinders(Principal principal, @PathVariable Long id) {
        
        Card card = cardserv.findOne(id);
        
        List<Bundle> bundles = null;
        if(null != card) {
            List<Card> cards = cardserv.findByName(card.getName());
            Validate.notEmpty(cards);
            
            bundles = new ArrayList<Bundle>();
            for (Card match : cards) {
                bundles.addAll(match.getBundles());
            }
        }
        
        return mav("card/find-in-binders")
                .addObject("card", card)
                .addObject("bundles", bundles);
    }

}
