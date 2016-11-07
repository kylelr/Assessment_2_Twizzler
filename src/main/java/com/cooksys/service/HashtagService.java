package com.cooksys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.entity.Hashtag;

import com.cooksys.repository.HashtagRepo;
@Service
public class HashtagService {
private HashtagRepo hashtagRepo;
	
	public HashtagService(HashtagRepo hashtagRepo) {
		this.hashtagRepo = hashtagRepo;
	}
	
	//public boolean exists(String hashtag)  {

		
	//}
	
	public List<Hashtag> getAllTags()  {

		return hashtagRepo.findAll();
	}
}
