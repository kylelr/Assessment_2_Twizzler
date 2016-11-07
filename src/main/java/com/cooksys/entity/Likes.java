package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

	 
	//private String Hastag;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "user_id")
    private Long user_id;

	@Column(name = "twizzle_id")
	private Long twizzle_id;
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getTwizzle_id() {
		return twizzle_id;
	}

	public void setTwizzle_id(Long twizzle_id) {
		this.twizzle_id = twizzle_id;
	}

	public Likes() {
			
	}
	

	

}
