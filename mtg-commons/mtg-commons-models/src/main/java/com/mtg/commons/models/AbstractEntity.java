package com.mtg.commons.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue
	protected long id;
	
	@Column(name = "name", nullable = false)
	protected String name;
	
	@Column(name = "url_fragment")
	protected String urlFragment;
	
	@Column(name = "description", nullable = false)
	@Type(type="text")
	protected String description = "";

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	protected Image image;
	
	public AbstractEntity() {
		//
	}
	
	public AbstractEntity(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlFragment() {
		return urlFragment;
	}

	public void setUrlFragment(String urlFragment) {
		this.urlFragment = urlFragment;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
