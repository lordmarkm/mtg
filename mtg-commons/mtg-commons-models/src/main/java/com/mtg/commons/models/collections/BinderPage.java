package com.mtg.commons.models.collections;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="binderpages", uniqueConstraints={
		@UniqueConstraint(columnNames={"binder_id", "pageNumber"})
})
public class BinderPage {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="binder_id")
	private Binder binder;
	
	@Column(name="pageNumber", nullable=false)
	private int pageNumber;
	
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Bundle> bundles;

	public BinderPage() {
		//
	}
	
	public BinderPage(Binder binder, int pageNumber) {
		this.binder = binder;
		this.pageNumber = pageNumber;
	}
	
	public Binder getBinder() {
		return binder;
	}

	public void setBinder(Binder binder) {
		this.binder = binder;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<Bundle> getBundles() {
		if(null == bundles) {
			this.bundles = new ArrayList<Bundle>();
		}
		return bundles;
	}

	public void setBundles(List<Bundle> bundles) {
		this.bundles = bundles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
