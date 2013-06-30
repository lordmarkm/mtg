package com.mtg.commons.models.interactive;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "comments")
public class Comment implements Commentable {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Comment comment;
	
	@ManyToOne
	private Post post;
	
	@OneToMany
	private List<Comment> replies;

	@Column
	@Type(type="text")
	private String text;
	
	public Commentable getParent() {
		return post != null ? post : comment;
	}
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
