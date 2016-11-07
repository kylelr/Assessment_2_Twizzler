package com.cooksys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "user") //set unique to true
public class User {

	public User(){
		
	}
	
	public User(String username) {
		this.username = username;
	}
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @Column(name = "username", unique = true)
	    private String username;
	    
	    @Column(name = "profile_id")
	    private String profile_id;
	    
	    //@Column(name = "joined")
	    //private Timestamp joined;
	    
	    @Column (name = "following")
	    private String[] following;
	    
		@Column (name = "follower")
        private String[] follower;    
		
		@Column(name="deleted")
		private boolean deleted;
		
		@OneToMany(mappedBy = "user")
		@JsonIgnore
		private List<Twizzle> twizzle;
		
		//join test
		//@JoinColumn
		//@JsonIgnore
		//private Profile Id;
	    
		private User(Long id, String profile_id, //Timestamp joined,
				List<Twizzle> twizzle, 
				String following, String follower) {
			this.id = id;
			this.profile_id = profile_id;
			//this.joined = joined;
			//this.following = following;
			//this.follower = follower;
			this.twizzle = twizzle;
		}
	    
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getUser() {
	        return username;
	    }

	    public void setUser(String user) {
	        this.username = username;
	    }
	   
	    
	    public String getProfile() {
			return profile_id;
		}

		public void setProfile(String profile_id) {
			this.profile_id = profile_id;
		}

		//public Timestamp getJoined() {
			//return joined;
		//}

		//public void setJoined(Timestamp joined) {
			//this.joined = joined;
		//}

		public String[] getFollowing() {
			return following;
		}

		public void setFollowing(String[] following) {
			this.following = following;
		}

		public String[] getFollower() {
			return follower;
		}

		public void setFollower(String[] follower) {
			this.follower = follower;
		}

		public boolean isDeleted() {
			return deleted;
		}

		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		
	
}
