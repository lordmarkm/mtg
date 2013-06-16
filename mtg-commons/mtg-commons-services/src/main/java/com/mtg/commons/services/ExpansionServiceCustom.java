package com.mtg.commons.services;

import java.util.List;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.Expansion;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.Rarities.Rarity;

public interface ExpansionServiceCustom {

	Image saveImage(String code, byte[] image);
	Image saveSymbol(String code, Rarity rarity, byte[] image);
	
	Expansion setCards(Expansion exp, List<Card> cards);
	
}
