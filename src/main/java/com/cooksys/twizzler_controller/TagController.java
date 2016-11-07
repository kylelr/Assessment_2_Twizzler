package com.cooksys.twizzler_controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Hashtag;
import com.cooksys.entity.Twizzle;
import com.cooksys.service.HashtagService;
import com.cooksys.service.TwizzleService;

@RestController
@RequestMapping("tags")
public class TagController {

	//GET tags
	HashtagService hashtagService; 
	TwizzleService twizzleService;
	
	public TagController(HashtagService hastagService,TwizzleService twizzleService) {
		this.hashtagService = hastagService;
		this.twizzleService = twizzleService;
	}


	@GetMapping("/")
	public List<Hashtag> getAllHashtags() {
		System.out.println("GETTING ALL TAGS");
		/*List<Hashtag> hashtags = new ArrayList<Hashtag>();
		System.out.println("GOT LABEL:" + label);
		Hashtag hashtag = new Hashtag();
		hashtag.setLabel("WhutItBeLike");
		hashtags.add(hashtag);*/
		
		return hashtagService.getAllTags();
		
	}
	
	@GetMapping("/{label}")
	public List<Twizzle> get(@PathVariable String label) {
		
		label = "#" + label;
		System.out.println("GOT LABEL:" + label);
		return twizzleService.getAllTwizzlesByHashtag(label);
	}
	
}
