package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cooksys.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value="SELECT * from twizzler.user where username=?", nativeQuery = true)
	public User getUserByUsername(String username);

}
