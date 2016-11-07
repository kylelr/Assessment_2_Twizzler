package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "credentials") 
public class Credentials {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//may need to remove join
	    @JoinColumn (name = "username")
		private String username;
	    
	    @Column (name = "password")
		private String password;
	    
	   public Credentials(){
		   
	   }
	   
	   public Credentials(Long id, String username, String password) {
		   this.setId(id);
		   this.username = username;
		   this.setPassword(password);
	   }
	   
		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}
}


