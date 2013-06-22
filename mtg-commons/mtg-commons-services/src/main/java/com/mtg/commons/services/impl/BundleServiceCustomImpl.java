package com.mtg.commons.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.services.BundleService;
import com.mtg.commons.services.BundleServiceCustom;

@Transactional
public class BundleServiceCustomImpl implements BundleServiceCustom {

	@Resource
	private BundleService bundles;
	
	@Override
	public void excise(Long id) {
		Bundle bundle = bundles.findOne(id);
		Validate.notNull(bundle);
		
		//disassociate from binderpage
		BinderPage page = bundle.getPage();
		page.getBundles().remove(bundle);
		
		//mark binder as modified
		page.getBinder().setLastModified(DateTime.now());
		
		//disassociate from card
		Card card = bundle.getCard();
		card.getBundles().remove(bundle);
		
		bundles.delete(bundle);
	}

}
