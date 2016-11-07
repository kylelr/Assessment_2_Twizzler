package com.cooksys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hashtag")
public class Hashtag {
	
	public Hashtag() {
		
	}
	
	public Hashtag(String hashtag) {
		this.setLabel(hashtag);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	//private String Hastag;
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	 
	@Column(name = "label")//, unique=true)
    private String label;

	@JoinColumn
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Twizzle> twizzleList;
	
	
	
	//public String getHastag() {
	//	return Hastag;
	

	//public void setHastag(String hastag) {
	//	Hastag = hastag;
	

}
