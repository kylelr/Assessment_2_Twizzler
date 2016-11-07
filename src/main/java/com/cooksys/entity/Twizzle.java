package com.cooksys.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * need
 *  author: 'User',
  posted: 'timestamp'
 * inReplyTo?: 'Tweet',
  repostOf?: 'Tweet'
 * 
 */

@Entity
@Table(name = "twizzle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Twizzle implements Serializable{
	

	private static final long serialVersionUID = 6095606284105237436L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "added")
	private Timestamp timestamp;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "in_reply_toId")
	private Long inReplyToId;
	
	@Column(name = "repostOfId")
	private Long repostOfId;
	
	@Column(name="deleted")
	private boolean deleted;
	//create "posted" TimeStamp
	@JoinColumn
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Hashtag> hashtagList;

	//test Mapping
	@ManyToOne
    @JoinColumn
    //@JsonIgnore
    private  User user;
	
	public Twizzle() {
		
	}
	public Twizzle(Long id, String content) { //Twizzle repostOf 
		this.id = id;
		this.content = content;
		//this.repostOf = repostOf;
	}

	public Twizzle(String content) {
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void addHashtag(Hashtag hashtag)   {
		getHashtagList().add(hashtag);
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Hashtag> getHashtagList() {
		return hashtagList;
	}
	public void setHashtagList(List<Hashtag> hashtagList) {
		this.hashtagList = hashtagList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Long getInReplyToId() {
		return inReplyToId;
	}
	public void setInReplyToId(Long inReplyToId) {
		this.inReplyToId = inReplyToId;
	}
	public Long getRepostOfId() {
		return repostOfId;
	}
	public void setRepostOfId(Long repostOfId) {
		this.repostOfId = repostOfId;
	}
	

	

	
	
}