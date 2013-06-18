package com.mtg.commons.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.BinderServiceCustom;
import com.mtg.commons.services.PageService;

@Transactional
public class BinderServiceCustomImpl extends AbstractEntityService implements BinderServiceCustom {

    private static Logger log = LoggerFactory.getLogger(BinderServiceCustomImpl.class);
    
    @Resource
    private BinderService binders;

    @Resource
    private PageService pages;
    
    @Override
    public Binder create(MagicPlayer owner, Binder binder) {
        
        if(null != binders.findByOwnerAndUrlFragment(owner.getName(), urlfragment(binder.getName()))) {
            log.error("Reject duplicate binder name. owner={}, name={}", owner.getName(), binder.getName());
            return null;
        }
        
        binder.setOwner(owner);
        binder.setUrlFragment(urlfragment(binder.getName()));
        Binder saved = binders.save(binder);
        
        owner.getBinders().add(saved);
        
        return saved;
    }

	@Override
	public Bundle addCard(Binder binder, int page, Card card) {
		Validate.notNull(binder);
		Validate.notNull(card);
		Validate.isTrue(page != 0);
		
		BinderPage binderPage = pages.findPage(binder.getId(), page);
		if(null == binderPage) {
			List<BinderPage> pages = binder.getPages(); //initializes the list if null
			for(BinderPage bp : pages) {
				if(bp.getPageNumber() == page) binderPage = bp;
			}
		}
		
		Validate.notNull(binderPage);
		
		Bundle bundle = new Bundle(card);
		bundle.setPage(binderPage);
		
		//card.getBundles().add(bundle);
		binderPage.getBundles().add(bundle);
		
		return null;
	}

	@Override
	public void excise(Binder binder) {
		
		binder.getOwner().getBinders().remove(binder);
		binders.delete(binder);
		
	}
    
}
