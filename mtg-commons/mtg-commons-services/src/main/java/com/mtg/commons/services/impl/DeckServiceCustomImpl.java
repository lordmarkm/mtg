package com.mtg.commons.services.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Deck;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.DeckService;
import com.mtg.commons.services.DeckServiceCustom;
import com.mtg.commons.services.ImageService;
import com.mtg.commons.services.PlayerService;

@Transactional
public class DeckServiceCustomImpl extends AbstractEntityService implements DeckServiceCustom {

	@Resource
	private DeckService decks;
	
	@Resource
	private ImageService images;
	
	@Resource
	private PlayerService accounts;
	
	@Override
	public Deck create(String owner, Deck deck) {
		deck.setUrlFragment(urlfragment(deck.getName()));
		Deck saved = decks.save(deck);
	
		MagicPlayer player = accounts.findByName(owner);
		saved.setOwner(player);
		player.getDecks().add(saved);
		
		return saved;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void excise(Deck deck) {
		images.excise(deck.getImage());
		decks.delete(deck);
	}

    @Override
    public void addcard(Deck deck, Card card) {
        deck.getCards().add(card);
    }

}
