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

	
}
