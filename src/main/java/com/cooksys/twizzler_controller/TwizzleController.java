package com.cooksys.twizzler_controller;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Hashtag;
import com.cooksys.entity.Mention;
import com.cooksys.entity.Twizzle;
import com.cooksys.entity.User;
import com.cooksys.requests.TwizzleRequest;
import com.cooksys.service.TwizzleService;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("twizzler")
public class TwizzleController {
	
	TwizzleService twizzleService;
	UserService userService;
	
	public TwizzleController(TwizzleService twizzleService, UserService userService) {
		this.twizzleService = twizzleService;
		this.userService = userService;
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON)
	public Twizzle  add(@RequestBody TwizzleRequest twizzleRequest) {
	
			Twizzle twizzle = new Twizzle(twizzleRequest.getContent());
			User user = null;
			
			if(!userService.exists("@" +twizzleRequest.getCredentials().getUsername())){
				user = new User("@" + twizzleRequest.getCredentials().getUsername());
				userService.add(user);
			}else{
				user = userService.getUserbyUsername(twizzleRequest.getCredentials().getUsername());
			}
			
			 
			twizzle.setUser(user);		
			twizzle.setTimestamp(new Timestamp(new Date().getTime()));			
			twizzle.setHashtagList(getHashTagList(twizzleRequest.getContent()));
			
			Twizzle newTwizzle = twizzleService.add(twizzle);					
		
			twizzleService.twizzleMentions(getMentionsList(twizzleRequest.getContent(), newTwizzle.getId()));
			
			return newTwizzle;
		}
	
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
	public Twizzle  deleteTwizzle(@RequestBody Credentials credentials, @PathVariable String id) {
		
		twizzleService.deleteTwizzlerById(Long.valueOf(id));
		
		/*
		 * DELETE tweets/{id}

			"Deletes" the tweet with the given id. If no such tweet exists or the provided credentials do not match author of the tweet, an error should be sent in lieu of a response. If a tweet is successfully "deleted", the response should contain the tweet data prior to deletion.
			
			IMPORTANT: This action should not actually drop any records from the database! Instead, develop a way to keep track of "deleted" tweets so that even if a tweet is deleted, data with relationships to it (like replies and reposts) are still intact.
			
			Request
			
			'Credentials'
			Response
			
			'Tweet'
		 */
		return twizzleService.get(Long.valueOf(id));
	}
	
	@GetMapping(value = "/")
	public List<Twizzle>  getAllTweets() {
		return twizzleService.getAllTwizzlesNonNullOrdered();
	}
	
	
	@GetMapping(value = "/{id}")
	public Twizzle  getTweetById(@PathVariable String id) {
		/*
		 * GET tweets/{id}

			Retrieves a tweet with a given id. If no such tweet exists, or the given tweet is deleted, an error should be sent in lieu of a response.
			
			Response
			
			'Tweet'
		 */
		return twizzleService.get(Long.valueOf(id));
	}
	

	@PostMapping(value = "/{id}/like", consumes = MediaType.APPLICATION_JSON)
	public void  likeTwizzle(@RequestBody TwizzleRequest twizzleRequest, @PathVariable String id){
		twizzleService.likeTwizzle(twizzleRequest.getCredentials().getId(), Long.valueOf(id));	
		/*
		 * POST tweets/{id}/like

			Creates a "like" relationship between the tweet with the given id and the user whose credentials are provided by the request body. If the tweet is deleted or otherwise doesn't exist, or if the given credentials do not match an active user in the database, an error should be sent. Following successful completion of the operation, no response body is sent.
			
			Request
			
			'Credentials'

		 */
	}
	
	@PostMapping(value = "/{id}/reply", consumes = MediaType.APPLICATION_JSON)
	public void  replyToTwizzle(@RequestBody TwizzleRequest twizzleRequest, @PathVariable String id){
		
		/**
		 * POST tweets/{id}/reply

			Creates a reply tweet to the tweet with the given id. 
			The author of the newly-created tweet should match the credentials provided by the request body.
			If the given tweet is deleted or otherwise doesn't exist, 
			or if the given credentials do not match an active user in the database,
			 an error should be sent in lieu of a response.
			
			Because this creates a reply tweet, content is not optional. 
			Additionally, notice that the inReplyTo property is not provided by the request.
			 The server must create that relationship.
			
			The response should contain the newly-created tweet.
			
			IMPORTANT: when a tweet with content is created, the server must process the tweet's content for
			 @{username} mentions and #{hashtag} tags. 
			 There is no way to create hashtags or 
			 create mentions from the API, so this must be handled automatically!
			
			Request
			
			{
			  content: 'string',
			  credentials: 'Credentials'
			}
			Response
			
			'Tweet'

		 */
		Twizzle twizzle = new Twizzle(twizzleRequest.getContent());
		twizzle.setInReplyToId(Long.valueOf(id));
		
		twizzle.setHashtagList(getHashTagList(twizzleRequest.getContent()));
		
		User user = null;
		
		if(!userService.exists(twizzleRequest.getCredentials().getUsername())){
			user = new User(twizzleRequest.getCredentials().getUsername());
			userService.add(user);
		}else{
			user = userService.getUserbyUsername(twizzleRequest.getCredentials().getUsername());
		}
		
		 
		twizzle.setUser(user);		
		twizzle.setTimestamp(new Timestamp(new Date().getTime()));
		
		Twizzle newTwizzle = twizzleService.add(twizzle);					
		
		twizzleService.twizzleMentions(getMentionsList(twizzleRequest.getContent(), newTwizzle.getId()));
		
		
	}
	
	@GetMapping(value = "/{id}/mentions")
	public List<User> getMentionedUsers(@PathVariable String id){
		
		
		List<String> mentionList = twizzleService.getMentionByTwizzleId(Long.valueOf(id));
		List<User> userList = new ArrayList<User>();
		
		for(String name : mentionList){
			userList.add(userService.getUserbyUsername(name));
		}
		return userList;
		/**
		 * GET tweets/{id}/mentions

		Retrieves the users mentioned in the tweet with the given id.
		If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
		
		Deleted users should be excluded from the response.
		
		IMPORTANT Remember that tags and mentions must be parsed by the server!
		
		Response
		
		['User']
		 */
	}
	
	@GetMapping(value = "/{id}/likes")
	public List<User> getLikedUsers(@PathVariable String id){
		
		
		List<String> likeList = twizzleService.getMentionByTwizzleId(Long.valueOf(id));
		List<User> userList = new ArrayList<User>();
		
		for(String name : likeList){
			userList.add(userService.getUserbyUsername(name));
		}
		return userList;
		/**
		 * GET tweets/{id}/likes

	Retrieves the active users who have liked the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

	Deleted users should be excluded from the response.

	Response

	['User']		
	 */
	}
	
	
	
	private List<Hashtag> getHashTagList(String content){
		
		List<Hashtag> tagList = new ArrayList<Hashtag>();		
		
		if (content != null){
			
			Pattern p = Pattern.compile("#\\w+");   // the pattern to search for
		    Matcher m = p.matcher(content);

		    // if we find a match, get the group
		    
		    while (m.find())
		    {
		        String rawHashTag = m.group();			      			      		    
				Hashtag hashTag = new Hashtag(rawHashTag);
				tagList.add(hashTag);
		    }
		}
		
		return tagList;
	}
	
	private List<Mention> getMentionsList(String content, Long twizzle_id){
		
		List<Mention> mentionList = new ArrayList<Mention>();		
		
		if (content != null){
			
			Pattern p = Pattern.compile("@\\w+");   // the pattern to search for
		    Matcher m = p.matcher(content);

		    // if we find a match, get the group
		    
		    while (m.find())
		    {
		        String userName = m.group();			      			      		    
				Mention mention = new Mention(twizzle_id,userName);
				mentionList.add(mention);
		    }
		}
		
		return mentionList;
	}
	
	@PostMapping(value = "/{id}/repost", consumes = MediaType.APPLICATION_JSON)
	public Twizzle  add(@RequestBody Credentials credentials, @PathVariable String id) {
			
			Twizzle originalTwizzle = twizzleService.get(Long.valueOf(id));
			
			
			Twizzle twizzle = new Twizzle();
			twizzle.setContent(originalTwizzle.getContent());
			
			User user = null;
			
			if(!userService.exists("@" + credentials.getUsername())){
				user = new User("@" + credentials.getUsername());
				userService.add(user);
			}else{
				user = userService.getUserbyUsername(credentials.getUsername());
			}
					 
			twizzle.setUser(user);
			twizzle.setRepostOfId(originalTwizzle.getId());
			twizzle.setTimestamp(new Timestamp(new Date().getTime()));			
			twizzle.setHashtagList(getHashTagList(originalTwizzle.getContent()));
			
			Twizzle newTwizzle = twizzleService.add(twizzle);						
			twizzleService.twizzleMentions(getMentionsList(twizzle.getContent(), twizzle.getId()));
			
			return newTwizzle;
		}
	
	@GetMapping(value = "/{id}/reposts")
	public List<Twizzle> getReposts(@PathVariable String id){
		return twizzleService.getTwizzleReposts(Long.valueOf(id));
		/**
		 * GET tweets/{id}/reposts

	Retrieves the direct reposts of the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

	Deleted reposts of the tweet should be excluded from the response.

	Response

	['Tweet']
		 */
	}
	
	@GetMapping(value = "/{id}/replies")
	public List<Twizzle> getReplies(@PathVariable String id){
		return twizzleService.getTwizzleReplies(Long.valueOf(id));
	}
	
	@GetMapping(value = "/{id}/tags")
	public List<String> getHashTagsByTwizzleId(@PathVariable String id){
		return twizzleService.getHashtagsByTwizzleId(Long.valueOf(id));
	}
	
/*




GET tweets/{id}/context

Retrieves the context of the tweet with the given id. If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.

IMPORTANT: While deleted tweets should not be included in the before and after properties of the result, transitive replies should. What that means is that if a reply to the target of the context is deleted, but there's another reply to the deleted reply, the deleted reply should be excluded but the other reply should remain.

Response

'Context'

	 */

}
