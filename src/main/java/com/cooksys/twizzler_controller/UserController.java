package com.cooksys.twizzler_controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Twizzle;
import com.cooksys.entity.User;
import com.cooksys.requests.CreateUserRequest;
import com.cooksys.requests.DeleteUserRequest;
import com.cooksys.requests.FollowUserRequest;
import com.cooksys.requests.TwizzleRequest;
import com.cooksys.requests.UnFollowUserRequest;
import com.cooksys.requests.UpdateUserRequest;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	UserService userService;
	//public UserController() {
		
	//}
	
	public UserController(UserService service) {
		this.userService = service;
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {	
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/{username}")
	public boolean getUser(@PathVariable String username) {
		System.out.println("getUser");
		
		return false;
		
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON)
	public User  createUser(@RequestBody CreateUserRequest request) {
		System.out.println("createUser");
			return null;	
	}
	
	
	
	@PatchMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON)
	public User  updateUser(@RequestBody UpdateUserRequest request, @PathVariable String username) {
		System.out.println("updateUser");
		/*
		PATCH users/@{username}

		Updates the profile of a user with the given username. If no such user exists, the user is deleted, or the provided credentials do not match the user, an error should be sent in lieu of a response. In the case of a successful update, the returned user should contain the updated data.

		Request

		{
		  credentials: 'Credentials',
		  profile: 'Profile'
		}
		Response

		'User'
		*/
			return null;	
	}	
	
	@DeleteMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON)
	public User  deleteUser(@RequestBody DeleteUserRequest request, @PathVariable String username) {
		System.out.println("deleteUser");
		/*
		 * DELETE users/@{username}

			"Deletes" a user with the given username. If no such user exists or the provided credentials do not match the user, an error should be sent in lieu of a response. If a user is successfully "deleted", the response should contain the user data prior to deletion.
		
			IMPORTANT: This action should not actually drop any records from the database! Instead, develop a way to keep track of "deleted" users so that if a user is re-activated, all of their tweets and information are restored.
		
			Request
		
			'Credentials'
			Response
		
			'User'
		 */
		return null;
	}
	
	@PostMapping(value = "/@{username}/follow", consumes = MediaType.APPLICATION_JSON)
	public void  followUser(@RequestBody FollowUserRequest request) {
		System.out.println("followUser");
		
	}
	
	@PostMapping(value = "/@{username}/unfollow", consumes = MediaType.APPLICATION_JSON)
	public void  unFollowUser(@RequestBody UnFollowUserRequest request) {
		System.out.println("unFollowUser");
		/*
		 * POST users/@{username}/unfollow

	Unsubscribes the user whose credentials are provided by the request body from the user whose username is given in the url. If there is no preexisting following relationship between the two users, no such followable user exists (deleted or never created), or the credentials provided do not match an active user in the database, an error should be sent as a response. If successful, no data is sent.

	Request

	'Credentials'*/
		
	
	}
	
	@GetMapping("/@{username}/feed")
	public List<Twizzle> getUserFeed(@PathVariable String username) {
		System.out.println("getUserFeed");
		/*
		 * GET users/@{username}/feed

			Retrieves all (non-deleted) tweets authored by the user with the given username, as well as all (non-deleted) tweets authored by users the given user is following. This includes simple tweets, reposts, and replies. The tweets should appear in reverse-chronological order. If no active user with that username exists (deleted or never created), an error should be sent in lieu of a response.
		
			Response
		
			['Tweet']
		 */
		return null;		
	}
	
	@GetMapping("/@{username}/tweets")
	public List<Twizzle> getUserTweets(@PathVariable String username) {
		System.out.println("getUserTweets");
		/*
		 * GET users/@{username}/tweets

			Retrieves all (non-deleted) tweets authored by the user with the given username. This includes simple tweets, reposts, and replies. The tweets should appear in reverse-chronological order. If no active user with that username exists (deleted or never created), an error should be sent in lieu of a response.

			Response

			['Tweet']
		 */
		return null;		
	}
	
	@GetMapping("/@{username}/mentions")
	public List<Twizzle> getUserMentions(@PathVariable String username) {
		System.out.println("getUserMentions");
		/*
		 *GET users/@{username}/mentions

			Retrieves all (non-deleted) tweets in which the user with the given username is mentioned. The tweets should appear in reverse-chronological order. If no active user with that username exists, an error should be sent in lieu of a response.
		
			A user is considered "mentioned" by a tweet if the tweet has content and the user's username appears in that content following a @.
		
			Response
		
			['Tweet']
		 */
		return null;		
	}
	
	@GetMapping("/@{username}/followers")
	public List<Twizzle> getUserFollowers(@PathVariable String username) {
		System.out.println("getUserFollowers");
		/*
		 * GET users/@{username}/followers

			Retrieves the followers of the user with the given username. Only active users should be included in the response. If no active user with the given username exists, an error should be sent in lieu of a response.
		
			Response
		
			['User']
		 */
		return null;		
	}
	
	@GetMapping("/@{username}/following")
	public List<Twizzle> getUserFollowing(@PathVariable String username) {
		System.out.println("getUserFollowing");
		/*

		GET users/@{username}/following

		Retrieves the users followed by the user with the given username. Only active users should be included in the response. If no active user with the given username exists, an error should be sent in lieu of a response.

		Response

		['User']
		*/
		return null;		
	}
	
	
}
