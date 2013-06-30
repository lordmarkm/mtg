package com.mtg.web.dto;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.domain.PageRequest;

public class PageRequestDto {

	private int page;
	private int size;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("page", page)
			.append("size", size)
			.toString();
	}
	
	public PageRequest toPageRequest() {
		PageRequest pageRequest = new PageRequest(page, size);
		return pageRequest;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
