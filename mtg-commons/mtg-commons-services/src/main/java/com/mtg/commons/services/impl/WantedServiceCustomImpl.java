package com.mtg.commons.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Wanted;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.WantedService;
import com.mtg.commons.services.WantedServiceCustom;

@Transactional
public class WantedServiceCustomImpl implements WantedServiceCustom {

	@Resource
	private WantedService wanteds;
	
	@Override
	public void excise(Long id) {
		Wanted wanted = wanteds.findOne(id);
		Validate.notNull(wanted);
		
		//disassociate from binderpage
		MagicPlayer player = wanted.getWanter();
		player.getWanted().remove(wanted);
		
		//disassociate from card
		Card card = wanted.getCard();
		card.getBundles().remove(wanted);
		
		wanteds.delete(wanted);
	}

}
