package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ParseCardsForm {

	@NotEmpty(message = "Expansion code not specified")
	private String expcode;
	
	@NotEmpty(message = "URL not specified")
	private String url;

	public String getExpcode() {
		return expcode;
	}

	public void setExpcode(String expcode) {
		this.expcode = expcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
