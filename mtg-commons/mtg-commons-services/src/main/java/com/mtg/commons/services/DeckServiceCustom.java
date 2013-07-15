package com.mtg.commons.services;

import com.mtg.commons.models.collections.Deck;

public interface DeckServiceCustom {

	public Deck create(String owner, Deck deck);
	public void excise(Deck deck);
	
}
