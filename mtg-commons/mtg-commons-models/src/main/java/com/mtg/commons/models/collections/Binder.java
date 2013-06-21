package com.mtg.commons.models.collections;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
    
	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")    
    private DateTime lastModified;
    
    public int cardCount() {
    	int count = 0;
    	for(BinderPage page : getPages()) {
    		for(Bundle bundle : page.getBundles()) {
    			count += bundle.getCount();
    		}
    	}
    	return count;
    }
    
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

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}
    
}
