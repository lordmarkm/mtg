package com.mtg.commons.models.interactive;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.mtg.commons.models.magic.MagicPlayer;


@Entity
@Table(name = "comments")
public class Comment implements Commentable {

    public static final String PREFERRED_MODEL_KEY = "comment";

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime postdate;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Post post;

    @OneToMany
    @OrderBy("postdate desc")
    private List<Comment> replies;

    @Column
    private int replyCount = 0;

    @Column
    @Type(type="text")
    private String text;

    @ManyToOne
    private MagicPlayer author;

    @Column
    private boolean deleted = false;
    
    public Post getProgenitor() {
		Comment lastComment = this;
		Post progenitor = null;
		while(null == progenitor) {
			progenitor = lastComment.getPost();
			lastComment = lastComment.getComment();
		
			//nothing found, should never happen, but catch just in case
			if(null == lastComment) break;
		}
		return progenitor;
    }
    
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

    public MagicPlayer getAuthor() {
        return author;
    }

    public void setAuthor(MagicPlayer author) {
        this.author = author;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public DateTime getPostdate() {
        return postdate;
    }

    public void setPostdate(DateTime postdate) {
        this.postdate = postdate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
