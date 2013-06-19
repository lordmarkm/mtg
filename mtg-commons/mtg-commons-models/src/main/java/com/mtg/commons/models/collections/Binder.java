package com.mtg.commons.models.collections;

import java.util.ArrayList;
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

	public static final int PAGES = 20;
	
    @OneToMany(cascade=CascadeType.ALL)
    private List<BinderPage> pages;

    @ManyToOne
    private MagicPlayer owner;
    
    public List<BinderPage> initPages() {
        this.pages = new ArrayList<BinderPage>();
        
        //init pages 1 to PAGES
        for(int i = 1; i < PAGES + 1; i++) {
            pages.add(new BinderPage(this, i));
        }
        
        return this.pages;
    }
    
    public MagicPlayer getOwner() {
        return owner;
    }

    public void setOwner(MagicPlayer owner) {
        this.owner = owner;
    }

	public List<BinderPage> getPages() {
		if(null == pages) {
		    this.initPages();
		}
		return pages;
	}

	public void setPages(List<BinderPage> pages) {
		this.pages = pages;
	}
    
}
