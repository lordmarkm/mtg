package com.mtg.commons.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.services.BundleService;
import com.mtg.commons.services.BundleServiceCustom;
import com.mtg.commons.services.PageService;

@Transactional
public class BundleServiceCustomImpl implements BundleServiceCustom {

	@Resource
	private BundleService bundles;

	@Resource
	private PageService pages;
	
	@Override
	public void excise(Long id) {
		Bundle bundle = bundles.findOne(id);
		Validate.notNull(bundle);
		
		//disassociate from binderpage
		BinderPage page = bundle.getPage();
		List<Bundle> bundlelist = page.getBundles();
		bundlelist.remove(bundle);
		bundle.setPage(null);
		
		//mark binder as modified
		page.getBinder().setLastModified(DateTime.now());
		
		//disassociate from card
		bundle.setCard(null);
		
		bundles.delete(bundle);
	}

}
