package com.mtg.commons.models.interactive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="votes")
public class Vote {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private MagicPlayer voter;
	
	@Column
	private int score; //+1 or -1
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public MagicPlayer getVoter() {
		return voter;
	}

	public void setVoter(MagicPlayer voter) {
		this.voter = voter;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
