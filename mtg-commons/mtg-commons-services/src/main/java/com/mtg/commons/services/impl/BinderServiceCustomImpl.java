package com.mtg.commons.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.BinderPage;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.locations.Location.Type;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.service.support.Propkeys;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.BinderServiceCustom;
import com.mtg.commons.services.GenericLocationService;
import com.mtg.commons.services.ImageService;
import com.mtg.commons.services.PageService;

@Transactional
public class BinderServiceCustomImpl extends AbstractEntityService implements BinderServiceCustom {

    private static Logger log = LoggerFactory.getLogger(BinderServiceCustomImpl.class);
    
    @Resource
    private BinderService bindserv;

    @Resource
    private PageService pages;
    
    @Resource
    private GenericLocationService locations;
    
    @Resource
    private ImageService images;
    
    @Resource
    private Environment env;
    
    @Override
    public Binder create(MagicPlayer owner, Binder binder) {
        
    	String maxBindersStr = env.getProperty(Propkeys.maxBinders);
    	Validate.notNull(maxBindersStr);
    	Integer maxBinders = Integer.parseInt(maxBindersStr);
    	if(owner.getBinders().size() >= maxBinders) {
    		return null;
    	}
    	
        if(null != bindserv.findByOwnerAndUrlFragment(owner.getName(), urlfragment(binder.getName()))) {
            log.error("Reject duplicate binder name. owner={}, name={}", owner.getName(), binder.getName());
            return null;
        }
        
        binder.setOwner(owner);
        binder.setUrlFragment(urlfragment(binder.getName()));
        binder.setLastModified(DateTime.now());
        Binder saved = bindserv.save(binder);
        
        owner.getBinders().add(saved);
        
        return saved;
    }

	@Override
	public Bundle addCard(Binder binder, int page, Card card) {
		Validate.notNull(binder);
		Validate.notNull(card);
		Validate.isTrue(page != 0);
		
		BinderPage binderPage = null;//pages.findPage(binder.getId(), page);
		List<BinderPage> pages = binder.getPages(); //initializes the list if null
		
		log.debug("Pages: {}", pages);
		
		if(pages.isEmpty()) {
		    pages = binder.initPages();
		}
		for(BinderPage bp : pages) {
			if(bp.getPageNumber() == page) binderPage = bp;
		}
		
		Validate.notNull(binderPage);
		
		//limit bundles per page
		String maxBundlesStr = env.getProperty(Propkeys.maxBundles);
		Validate.notNull(maxBundlesStr);
		Integer maxBundles = Integer.valueOf(maxBundlesStr);
		if(binderPage.getBundles().size() >= maxBundles) {
			return null;
		}
		
		Bundle bundle = new Bundle(card);
		bundle.setPage(binderPage);
		
		card.getBundles().add(bundle);
		binderPage.getBundles().add(bundle);
		
		binder.setLastModified(DateTime.now());
		
		return bundle;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void excise(Binder binder) {
		
		binder.getOwner().getBinders().remove(binder);
		images.excise(binder.getImage());
		bindserv.delete(binder);
		
	}

	@Override
	public List<Binder> filterByLocation(Type type, Long id) {
		
		if(type == Type.all && id == 0) {
			return bindserv.findAll();
		}
		
		Validate.notNull(type);
		Validate.notNull(id);
		Validate.isTrue(id != 0);
		
//		List<MagicPlayer> players = locations.getPlayers(type, id);
//		
//		List<Binder> binders = new ArrayList<Binder>();
//		for(MagicPlayer player : players) {
//			binders.addAll(player.getBinders());
//		}

		//TODO use these instead to be ready for PageRequests
		//country - from Binder b where b.owner.country = :country
		//city    - from Binder b where :city in elements(b.owner.cities)
		//meetup  - from Binder b where :meetup in elements(b.owner.meetups)
		
		List<Binder> binders = new ArrayList<Binder>();
		
		switch(type) {
		case meetup:
			binders = bindserv.findByMeetup(id);
			break;
		case city:
			binders = bindserv.findByCity(id);
			break;
		case country:
			binders = bindserv.findByCountry(id);
			break;
		default:
			throw new IllegalArgumentException("Illegal location type: " + type);
		}
		
		return binders;
	}
    
}
