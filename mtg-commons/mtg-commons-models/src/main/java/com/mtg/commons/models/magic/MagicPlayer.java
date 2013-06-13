package com.mtg.commons.models.magic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.collections.Binder;


@Entity
@Table(name="magicplayers")
public class MagicPlayer extends AbstractEntity {

    @OneToMany
    private List<Binder> binders;

    public List<Binder> getBinders() {
        if(null == binders) {
            binders = new ArrayList<Binder>();
        }
        return binders;
    }

    public void setBinders(List<Binder> binders) {
        this.binders = binders;
    }
    
}
