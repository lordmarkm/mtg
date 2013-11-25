package com.mtg.parser.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.Image;
import com.mtg.parser.services.WebsiteParser;

/**
 * Parse a StarCityGames card list, such as http://sales.starcitygames.com/category.php?cat=5061&start=0
 * @author Mark
 */
@Service
public class SCGListParser implements WebsiteParser {

	private static Logger log = LoggerFactory.getLogger(SCGListParser.class);
	
	private Card readCard(Element cardrow) {
		Card card = new Card();
		
		//name, image, and link (SCG)
		Element column1 = cardrow.select(".search_results_1").first();
		if(null == column1) {
			log.debug("Skipping: {}", cardrow);
			return null;
		}
		
		Element nameImageLink = column1.select(".card_popup").first();
		if(null == nameImageLink) {
			log.debug("Skipping: {}", column1);
			return null;
		}
		
		//name
		String name = nameImageLink.text();
		card.setName(name);

		//link
		String href = nameImageLink.attr("href");
		card.getMetadata().setCardlink(href);
		
		//image
		String imageRel = nameImageLink.attr("rel");
		Element imagePopup = Jsoup.parse(imageRel);
		String imageString = imagePopup.select("img").first().attr("src");
		Image image = new Image();
		image.setOriginalPath(imageString);
		card.setImage(image);
		
		//casting cost
		Element column3 = cardrow.select(".search_results_3").first();
		String cost = parseCost(column3);
		if(null != cost) {
			card.setCost(cost);
		}
		
		//card types
		Element column4 = cardrow.select(".search_results_4").first();
		if(null != column4) {
			String[] types = column4.text().split("-");
			card.setSupertype(types[0].trim());
			if(types.length == 2) {
				card.setSubtype(types[1].trim());
			}
		}
		
		//skip column 5, P/T
		
		//rarity
		Element column6 = cardrow.select(".search_results_6").first();
		if(null != column6 && !StringUtils.isEmpty(column6.text())) {
			card.setRarity(column6.text());
		} else {
			card.setRarity("C");
		}
		
		return card;
	}
	
	@Override
	public String parseCost(Element td) {
		String cost = null;
		if(null != td) {
			StringBuilder build = new StringBuilder();
			build.append(td.text());
			
			for(Element coloredmana : td.select("img")) {
				String colorStr = coloredmana.attr("alt");
				if(colorStr.length() > 1) {
					colorStr = "{" + colorStr + "}";
				}
				build.append(colorStr);
			}
			
			cost = build.toString();
		}
		return cost;
	}
	
	
	@Override
	public Card parseOne(String url, String cardName) throws IOException {
		Document doc = Jsoup.connect(url).get();
		
		Element cardlink = doc.select(".card_popup:contains(" + cardName + ")").first();
		Validate.notNull(cardlink);
		
		Element cardrow = cardlink.parents().select(".deckdbbody_row,.deckdbbody2_row").first();
		Validate.notNull(cardrow);
		
		return readCard(cardrow);
	}
	
	
	
	
	@Override
	public List<Card> parse(String url) throws IOException {
		List<Card> cards = new ArrayList<Card>();
		
		Document doc = Jsoup.connect(url).get();
		
		Elements cardrows = doc.select(".deckdbbody_row,.deckdbbody2_row");
		if(null == cardrows) {
			return cards;
		}
		
		log.info("Parsing page. Elements = {}", cardrows.size());
		
		for(Element cardrow : cardrows) {
			log.debug("Parsing: {}", cardrow);
			
			Card card = readCard(cardrow);
			if(null == card) {
				continue;
			}
			cards.add(card);
		}
		
		return cards;
	}
	
	@Override
	public List<Card> parseAll(String url) throws IOException {
		log.info("Attempting to parse all cards in an expansion. root url={}", url);
		
		url = url.substring(0, url.lastIndexOf("=") + 1);
		int i = 0;
		List<Card> expansionCards = new ArrayList<Card>();
		
		List<Card> pageCards = null;
		while(null == pageCards || pageCards.size() != 0) {
			pageCards = parse(url + i);
			expansionCards.addAll(pageCards);
			i += 50;
		}
		
		return expansionCards;
	}

}
