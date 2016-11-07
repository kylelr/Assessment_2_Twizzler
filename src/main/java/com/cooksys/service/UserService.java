package com.cooksys.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Profile;
import com.cooksys.entity.User;
import com.cooksys.repository.UserRepo;

@Service
public class UserService {
	
	private UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
		
		
	}
	public User get(Long id) {
		return userRepo.getOne(id);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}

	public User add(User user) {
		return userRepo.saveAndFlush(user);
	}
	
	public User getUserbyUsername(String username){
		return userRepo.getUserByUsername(username);
	}
	
	public User deleteUser(String username){
		userRepo.deleteUserByName(username);
		return userRepo.getUserByUsername(username);
	}
	
	public boolean exists(String username)  {

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withMatcher("username", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
		
		Example<User> example = Example.of(new User(username), matcher);

		return userRepo.count(example) > 0 ? true : false;
	}
	
	

}
