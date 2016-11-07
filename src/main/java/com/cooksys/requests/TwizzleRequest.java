package com.cooksys.requests;

import java.io.Serializable;

import com.cooksys.entity.Credentials;

public class TwizzleRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706024426790848834L;

	
	private String content;
	private Credentials credentials;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	
	
}
