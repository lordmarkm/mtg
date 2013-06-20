package com.mtg.web.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mtg.commons.models.Card;

public class SearchResult {

	public enum SearchResultType {
		card
	}
	
	private SearchResultType type;
	private String name;
	private String id;
	
	/**
	 * In this method, if multiple cards have the same name, they are added to the same search 
	 * result by setting the id to card1.id,card2.id,card3.id and so on.
	 */
	public static List<SearchResult> distinctCardNameCommaSeparatedId(List<Card> cards) {
	    Map<String, SearchResult> results = new HashMap<String, SearchResult>();
	    
	    for(Card card : cards) {
	        SearchResult result = results.get(card.getName());
	        if(null == result) {
	            results.put(card.getName(), new SearchResult(SearchResultType.card, String.valueOf(card.getId()), card.getName()));
	        } else {
	            result.setId(result.getId() + "," + card.getId());
	        }
	    }
	    
	    return new ArrayList<SearchResult>(results.values());
	}
	
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
