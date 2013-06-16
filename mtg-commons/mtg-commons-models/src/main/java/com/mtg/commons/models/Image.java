package com.mtg.commons.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author mbmartinez
 */

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue
	private long id;

	@Column
	@Type(type="text")
	private String path;
	
	@Column
	@Type(type="text")
	private String originalPath;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
	
}
