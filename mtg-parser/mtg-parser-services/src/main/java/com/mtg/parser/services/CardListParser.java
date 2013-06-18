package com.mtg.parser.services;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;

import com.mtg.commons.models.Card;

/**
 * Parse a list of cards from a list type webpage
 * @author Mark
 */
public interface CardListParser {

	/**
	 * Parse one card
	 */
	Card parseOne(String url, String cardName) throws IOException;

	/**
	 * Parse one page
	 */
	List<Card> parse(String url) throws IOException;
	
	/**
	 * Parse everything in the expansion
	 */
	List<Card> parseAll(String url) throws IOException;

	/**
	 * This is here for unit tests only
	 */
	String parseCost(Element td);
	
}
