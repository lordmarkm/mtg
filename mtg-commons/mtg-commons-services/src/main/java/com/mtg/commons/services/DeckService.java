package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Deck;

public interface DeckService extends JpaRepository<Deck, Long>, DeckServiceCustom {

	/**
	 * @deprecated use excise(Deck) instead
	 */
	@Deprecated
	public void delete(Deck deck);

}
