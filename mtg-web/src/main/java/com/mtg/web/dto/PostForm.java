package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.interactive.Post;

public class PostForm {

	@NotEmpty(message = "Title can't be empty")
	private String title; //may be null for comments
	
	@NotEmpty(message = "Text can't be empty")
	private String text;
	
	private long parentId = 1; //may be null for frontpage posts

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("title", title)
			.append("text", text)
			.append("parentId", parentId)
			.toString();
	}
	
	public Post toPost() {
		Post post = new Post();
		post.setTitle(title);
		post.setText(text);
		post.setPostdate(DateTime.now());
		return post;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

}
