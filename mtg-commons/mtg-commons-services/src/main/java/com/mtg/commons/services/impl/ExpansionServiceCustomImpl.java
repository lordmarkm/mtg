package com.mtg.commons.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.Expansion;
import com.mtg.commons.models.Image;
import com.mtg.commons.models.Rarities;
import com.mtg.commons.services.ExpansionService;
import com.mtg.commons.services.ExpansionServiceCustom;
import com.mtg.commons.services.ImageService;

@Transactional
public class ExpansionServiceCustomImpl implements ExpansionServiceCustom {

	@Resource
	private ExpansionService exps;
	
	@Resource
	private ImageService images;
	
	@Override
	public Image saveImage(String code, byte[] image) {
		
		Expansion exp = exps.findByCode(code);
		Validate.notNull(exp);
		
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Image saveSymbol(String code, Rarities.Rarity rarity, byte[] image) {
		
		Expansion exp = exps.findByCode(code);
		Validate.notNull(exp);
		
		Image symbol = null;
		switch(rarity) {
		case common:
			symbol = exp.getRarities().getCommon();
			break;
		case uncommon:
			symbol = exp.getRarities().getUncommon();
			break;
		case rare:
			symbol = exp.getRarities().getRare();
			break;
		case mythic:
			symbol = exp.getRarities().getMythic();
			break;
		}
		
		Image supdated = images.update(symbol, image);
		return supdated;
	}

	@Override
	public Expansion setCards(Expansion exp, List<Card> cards) {
		Validate.notNull(exp);
		Validate.notNull(cards);
		
		for(Card card : cards) {
			card.setExpansion(exp);
		}
		exp.setCards(cards);
		
		return exps.save(exp);
	}

}
