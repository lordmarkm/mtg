package com.mtg.commons.models.collections;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="binders")
public class Binder extends AbstractEntity {

    @OneToMany(cascade=CascadeType.ALL)
    private List<Bundle> bundles;

    @ManyToOne
    private MagicPlayer owner;
    
    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

    public MagicPlayer getOwner() {
        return owner;
    }

    public void setOwner(MagicPlayer owner) {
        this.owner = owner;
    }
    
}
