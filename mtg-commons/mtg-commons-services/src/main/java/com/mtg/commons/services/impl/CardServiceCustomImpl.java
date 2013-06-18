package com.mtg.commons.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mtg.commons.models.Card;
import com.mtg.commons.services.CardServiceCustom;
import com.mtg.commons.services.ImageService;

public class CardServiceCustomImpl implements CardServiceCustom {
	
	//private static Logger log = LoggerFactory.getLogger(CardServiceCustomImpl.class);
	
	@Resource
	private ImageService images;

	@Override
	public List<String[]> toDataTableResponse(List<Card> cards) {
		List<String[]> dtResponse = new ArrayList<String[]>();
		for(Card card : cards) {
			dtResponse.add(new String[]{
				String.valueOf(card.getId()),
				card.getName(),
				card.getExpansion().getName(),
				card.getCost(),
				card.getRarity()
			});
		}
		return dtResponse;
	}

}
