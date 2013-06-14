package com.mtg.commons.services;

import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.magic.MagicPlayer;

@Transactional
public interface BinderServiceCustom {

    Binder create(MagicPlayer owner, Binder binder);

}
