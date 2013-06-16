package com.mtg.commons.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.mtg.commons.models.Card;
import com.mtg.commons.services.CardServiceCustom;

public class CardServiceCustomImpl implements CardServiceCustom {
	
	@Override
	public List<String[]> toDataTableReady(List<Card> cards) {
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
