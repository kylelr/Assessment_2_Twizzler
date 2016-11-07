package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
	//may need to remove join
	@Id
	@GeneratedValue
	private Long id;
	
	@Column (name = "first_name")
    private String first_name;
    
    @Column (name = "last_name")
    private String last_name;
    
    @Column (name = "email")
    private String email;
    
    @Column (name = "phone")
    private String phone;
    
    private Profile(Long id, String first_name, String last_name, String email, String phone) {
    	this.id = id;
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.email = email;
    	this.phone = phone;
    	
    }
    
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
