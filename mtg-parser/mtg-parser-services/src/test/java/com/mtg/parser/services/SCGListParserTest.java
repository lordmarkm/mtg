package com.mtg.parser.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.mtg.commons.models.Card;
import com.mtg.parser.services.impl.SCGListParser;

public class SCGListParserTest {

	private WebsiteParser parser = new SCGListParser();

	//@Test
	public void testParseOne() throws IOException {
		String url10e = "http://sales.starcitygames.com/category.php?cat=5061&start=0";
		Card amblaq = parser.parseOne(url10e, "Ambassador Laquatus");
		System.out.println(amblaq);
		assertNotNull(amblaq);
	}

	//@Test
	public void testParseHybrid() throws IOException {
		String url = "http://sales.starcitygames.com//category.php?cat=5094";
		Card adfae = parser.parseOne(url, "Advice from the Fae");
		Card bogram = parser.parseOne(url, "Boggart Ram-Gang");
		System.out.println(adfae);
		System.out.println(bogram);
	}

	//@Test
	public void testParseCC() {
		String cc = "<td class='deckdbbody search_results_3' nowrap='nowrap'>3" +
				" <img class='mana' width='13' height='13' border='0' alt='U' " +
				"src='http://8e8460c4912582c4e519-11fcbfd88ed5b90cfb46edba899033c9" +
				".r65.cf1.rackcdn.com/sales//images/U.gif'></img><img class='mana'" +
				" width='13' height='13' border='0' alt='U' src='http://8e8460c4912582c4e519-" +
				"11fcbfd88ed5b90cfb46edba899033c9.r65.cf1.rackcdn.com/sales//images/U.gif'>" +
				"</img><img class='mana' width='13' height='13' border='0' alt='U' " +
				"src='http://8e8460c4912582c4e519-11fcbfd88ed5b90cfb46edba899033c9.r65.cf1" +
				".rackcdn.com/sales//images/U.gif'></img></td>";

		Element td = Jsoup.parse(cc);
		String parsedCc = parser.parseCost(td);
		assertEquals("3UUU", parsedCc);
	}

	//@Test
	public void testParsePage() throws IOException {
		String url10e = "http://sales.starcitygames.com/category.php?cat=5061&start=0";
		List<Card> cards = parser.parse(url10e);

		System.out.println("Found " + cards.size() + " cards.");
		for(Card card : cards) {
			System.out.println(card);
		}
	}

	//@Test
	public void testParseAll() throws IOException {
		String url10e = "http://sales.starcitygames.com/category.php?cat=5061&start=0";

		List<Card> cards = parser.parseAll(url10e);
		for(Card card : cards) {
			System.out.println(card);
		}
	}

	//@Test
	public void testSubstring() {
		String url = "http://sales.starcitygames.com/category.php?cat=5061&start=0";
		assertEquals("http://sales.starcitygames.com/category.php?cat=5061&start=", url.substring(0, url.lastIndexOf("=") + 1));
	}

}
