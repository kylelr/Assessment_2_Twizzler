package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mention")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mention {
	
	public Mention(){
		
	}

	public Mention(Long twizzle_id,String userName){
		setTwizzle_id(twizzle_id);
		setUser_name(userName);
	}
	//private String Hastag;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "user_name")
    private String user_name;

	@Column(name = "twizzle_id")
	private Long twizzle_id;
	
	

	public long getTwizzle_id() {
		return twizzle_id;
	}

	public void setTwizzle_id(Long twizzle_id) {
		this.twizzle_id = twizzle_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

	

}
