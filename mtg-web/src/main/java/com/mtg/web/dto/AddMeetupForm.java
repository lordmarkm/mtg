package com.mtg.web.dto;

public class AddMeetupForm {

	private Long meetupId;
	
	private Long cityId;
	
	private String name;

	private String description;

	public Long getMeetupId() {
		return meetupId;
	}

	public void setMeetupId(Long meetupId) {
		this.meetupId = meetupId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
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
	
	
}
