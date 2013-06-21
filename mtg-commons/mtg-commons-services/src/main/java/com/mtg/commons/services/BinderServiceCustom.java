package com.mtg.commons.services;

import java.util.List;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.collections.Binder;
import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;

public interface BinderServiceCustom {

    Binder create(MagicPlayer owner, Binder binder);
    Bundle addCard(Binder binder, int page, Card card);
    void excise(Binder binder);
    List<Binder> filterByLocation(Location.Type type, Long id);
    
}
