package com.mtg.commons.models.interactive;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="posts")
public class Post implements Commentable {

    public static final String PREFERRED_MODEL_KEY = "post";
    
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	@Type(type="text")
	private String text;
	
	@Column
	@Type(type="text")
	private String link;
	
	@Column
	private String linkDomain;
	
	@ManyToOne(optional=false)
	private MagicPlayer author;
	
	@Column
	private int replyCount = 0;
	
	@OneToMany
	@OrderBy("postdate desc")
	private List<Comment> replies;
	
	@OneToMany
	private List<Vote> votes;
	
	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime postdate;

	@Embedded
	private PostParent parent;

	@Column
	@Type(type="text")
	private String urlFragment;
	
	@Column
	private boolean deleted = false;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("author", author.getName())
			.append("date", postdate)
			.toString();
	}
	
	public MagicPlayer getAuthor() {
		return author;
	}

	public void setAuthor(MagicPlayer author) {
		this.author = author;
	}
	
	public DateTime getPostdate() {
		return postdate;
	}

	public void setPostdate(DateTime postdate) {
		this.postdate = postdate;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
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

	public PostParent getParent() {
		return parent;
	}

	public void setParent(PostParent parent) {
		this.parent = parent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getUrlFragment() {
		return urlFragment;
	}

	public void setUrlFragment(String urlFragment) {
		this.urlFragment = urlFragment;
	}

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkDomain() {
		return linkDomain;
	}

	public void setLinkDomain(String linkDomain) {
		this.linkDomain = linkDomain;
	}


}
