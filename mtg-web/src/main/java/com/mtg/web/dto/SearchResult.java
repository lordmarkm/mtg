package com.mtg.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.mtg.commons.models.Card;

public class SearchResult {

	public enum SearchResultType {
		card
	}
	
	private SearchResultType type;
	private String name;
	private String id;
	
	public static List<SearchResult> transform(List<Card> cards) {
		
		List<SearchResult> results = new ArrayList<SearchResult>();
		for(Card card : cards) {
			results.add(new SearchResult(SearchResultType.card, String.valueOf(card.getId()), card.getName()));
		}

		return results;
	}
	
	public SearchResult(SearchResultType type, String id, String name) {
		this.type = type;
		this.id = id;
		this.name = name;
	}
	
	public SearchResultType getType() {
		return type;
	}
	public void setType(SearchResultType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
