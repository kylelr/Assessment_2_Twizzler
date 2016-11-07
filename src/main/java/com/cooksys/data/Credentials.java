package com.cooksys.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402563973305934061L;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
