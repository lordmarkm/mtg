package com.mtg.commons.services.impl;

import javax.annotation.Resource;

import com.mtg.commons.models.collections.Deck;
import com.mtg.commons.services.DeckService;
import com.mtg.commons.services.DeckServiceCustom;
import com.mtg.commons.services.ImageService;

public class DeckServiceCustomImpl implements DeckServiceCustom {

	@Resource
	private DeckService decks;
	
	@Resource
	private ImageService images;
	
	@SuppressWarnings("deprecation")
	@Override
	public void excise(Deck deck) {
		images.excise(deck.getImage());
		decks.delete(deck);
	}

}
