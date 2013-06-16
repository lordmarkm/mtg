package com.mtg.commons.services;

import java.util.List;

import com.mtg.commons.models.Card;

public interface CardServiceCustom {

	List<String[]> toDataTableReady(List<Card> cards);
	
}
