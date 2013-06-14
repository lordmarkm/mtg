package com.mtg.commons.services.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.BinderService;
import com.mtg.commons.services.BinderServiceCustom;

public class BinderServiceCustomImpl extends AbstractEntityService implements BinderServiceCustom {

    private static Logger log = LoggerFactory.getLogger(BinderServiceCustomImpl.class);
    
    @Resource
    private BinderService binders;

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
    
}
