package com.mtg.commons.models.interactive;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is a singleton
 * @author Mark
 */
@Entity
@Table(name = "frontpage")
public class Frontpage implements Postable {

	@OneToMany
	private List<Post> posts;

	@Id
	@GeneratedValue
	private long id;
	
	@Override
	public List<Post> getPosts() {
		if(null == posts) {
			posts = new ArrayList<Post>();		
		}
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
