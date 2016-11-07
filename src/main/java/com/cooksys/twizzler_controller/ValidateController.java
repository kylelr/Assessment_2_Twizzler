package com.cooksys.twizzler_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Hashtag;
import com.cooksys.service.HashtagService;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("validate")
public class ValidateController {
	
	HashtagService hashtagService;
	UserService validateUserService;

	public ValidateController(HashtagService hashtagService, UserService userService) {
		this.hashtagService = hashtagService;
		this.validateUserService = userService;
	}



	//GET validate/tag/exists/{label}
	@GetMapping("/tag/exists/{label}")
	public boolean tagExists(@PathVariable String label) {
		boolean exists = false;
	
		return exists;
	}
	
	//GET validate/username/exists/@{username}
	@GetMapping("/username/exists/{username}")
	public boolean userNameExists(@PathVariable String username) {
		boolean exists = false;
		exists = validateUserService.exists(username);
		return exists;
		
	}
	//GET validate/username/available/@{username}
	@GetMapping("/username/available/{username}")
	public boolean userNameAvailable(@PathVariable String username) {
		boolean exists = false;
		exists = validateUserService.exists(username);
		return exists;
		
	}
	
	/*
	GET validate/username/available/@{username}

	Checks whether or not a given username is available.

	Response

	'boolean'
*/
}
