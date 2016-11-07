package com.cooksys.twizzler_controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.cooksys.entity.Profile;
import com.cooksys.entity.Twizzle;
import com.cooksys.entity.User;
import com.cooksys.requests.CreateUserRequest;
import com.cooksys.requests.DeleteUserRequest;
import com.cooksys.requests.FollowUserRequest;
import com.cooksys.requests.TwizzleRequest;
import com.cooksys.requests.UnFollowUserRequest;
import com.cooksys.requests.UpdateUserRequest;
import com.cooksys.service.CredentialsService;
import com.cooksys.service.ProfileService;
import com.cooksys.service.TwizzleService;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	UserService userService;
	TwizzleService twizzleService;
	ProfileService profileService;
	CredentialsService credentialsService;
	
	public UserController(UserService service,TwizzleService twizzleService, ProfileService profileService, CredentialsService credentialsService) {
		this.userService = service;
		this.twizzleService = twizzleService;
		this.profileService = profileService;
		this.credentialsService=credentialsService;
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {	
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {		
		return userService.getUserbyUsername(username);	
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON)
	public User  createUser(@RequestBody CreateUserRequest request) {
		System.out.println("createUser");
		
		Profile profile = profileService.add(request.getProfile());
		User user = new User();
		user.setFollower(new String[]{});
		user.setFollowing(new String[]{});
		user.setProfile(String.valueOf(profile.getId()));
		user.setUsername("@" + request.getCredentials().getUsername());
		
		credentialsService.add(request.getCredentials());
		userService.add(user);
		return user;	
	}
	
	
	
	@PatchMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON)
	public User  updateUser(@RequestBody UpdateUserRequest request, @PathVariable String username) {
		System.out.println("updateUser");
		
		credentialsService.update(request.getCredentials());
		profileService.update(request.getProfile());
		
		User user = userService.getUserbyUsername("@" + username);
		
		return user;	
	}	
	
	@DeleteMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON)
	public User  deleteUser(@RequestBody DeleteUserRequest request, @PathVariable String username) {		 
		return userService.deleteUser(username);
	}
	
	@PostMapping(value = "/@{username}/follow", consumes = MediaType.APPLICATION_JSON)
	public User  followUser(@RequestBody FollowUserRequest request, @PathVariable String username) {
		System.out.println("USERNAME:" + username);
		User user = userService.getUserbyUsername("@" + username);
		String[] followers = user.getFollower();
		List<String> followerList = new ArrayList<String>();
		followerList.addAll(Arrays.asList(followers));
		followerList.add("@" + username);
		System.out.println(followerList.toString());

		user.setFollower(followerList.toArray(new String[0]));
		return userService.add(user);
		
	}
	
	@PostMapping(value = "/@{username}/unfollow", consumes = MediaType.APPLICATION_JSON)
	public void  unFollowUser(@RequestBody UnFollowUserRequest request, @PathVariable String username) {
		User user = userService.getUserbyUsername("@" + username);
		List<String> followerList = Arrays.asList(user.getFollower());
		followerList.remove(username);
		user.setFollower((String[])followerList.toArray());
		userService.add(user);
	}
	
	@GetMapping("/@{username}/feed")
	public List<Twizzle> getUserFeed(@PathVariable String username) {
		
		return twizzleService.getTwizzlesByUser("@" + username);		
	}
	
	@GetMapping("/@{username}/tweets")
	public List<Twizzle> getUserTweets(@PathVariable String username) {
		return twizzleService.getTwizzlesByUser(username);		
	}
	
	@GetMapping("/@{username}/mentions")
	public List<Twizzle> getUserMentions(@PathVariable String username) {		
		return twizzleService.getMentionsByUser(username);		
	}
	
	@GetMapping("/@{username}/followers")
	public List<String> getUserFollowers(@PathVariable String username) {
		User user = userService.getUserbyUsername("@" + username);
		List<String> followerList = Arrays.asList(user.getFollower());
		return followerList;		
	}
	
	@GetMapping("/@{username}/following")
	public List<String> getUserFollowing(@PathVariable String username) {
		User user = userService.getUserbyUsername("@" + username);
		List<String> followingList = Arrays.asList(user.getFollowing());
		return followingList;				
	}
	
	
}
