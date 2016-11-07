package com.cooksys.requests;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Profile;

@XmlRootElement
public class FollowUserRequest implements Serializable {

	private static final long serialVersionUID = 5881435118404191972L;

	private Credentials credentials;
	
	public FollowUserRequest(){
		
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
