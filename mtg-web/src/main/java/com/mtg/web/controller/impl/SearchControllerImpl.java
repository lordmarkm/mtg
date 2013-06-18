package com.mtg.web.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.mtg.commons.models.Card;
import com.mtg.commons.services.CardService;
import com.mtg.web.controller.SearchController;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.SearchResult;

@Component
public class SearchControllerImpl implements SearchController {

	private static Logger log = LoggerFactory.getLogger(SearchControllerImpl.class);
	
	@Resource
	private CardService cards;
	
	@Override
	public JSON navbar(@RequestParam("query") String query) {
		
		Validate.notNull(query);
		List<Card> res = cards.searchByName(query + "%");
		
		log.info("Search complete. Query={}, results={}", query, res.size());
		
		return JSON.ok().put("data", SearchResult.transform(res));
	}

}
