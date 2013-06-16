package com.mtg.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {
	
	MultipartFile data;

	public MultipartFile getData() {
		return data;
	}

	public void setData(MultipartFile data) {
		this.data = data;
	}
	
}
