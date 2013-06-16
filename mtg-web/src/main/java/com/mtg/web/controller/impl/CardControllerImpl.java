package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.Card;
import com.mtg.commons.services.CardService;
import com.mtg.web.controller.CardController;
import com.mtg.web.dto.JSON;
import com.mtg.web.support.DataTables;

@Component
public class CardControllerImpl extends GenericController implements CardController {

	private static Logger log = LoggerFactory.getLogger(CardControllerImpl.class);
	
	@Resource
	private CardService cardserv;
	
	@Override
	public ModelAndView card(Principal principal, @PathVariable Long id) {
		Card card = cardserv.findOne(id);
		log.info("Card view request. user={}, id={}, name={}", name(principal), id, card != null ? card.getName() : "Not found");
		return mav("card").addObject("card", cardserv.findOne(id));
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
				.put(DataTables.DATA, cardserv.toDataTableReady(page.getContent()));
	}

}
