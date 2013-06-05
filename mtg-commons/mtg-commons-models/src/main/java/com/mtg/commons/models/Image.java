package com.mtg.commons.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author mbmartinez
 */

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
