package com.cooksys.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.entity.Hashtag;
import com.cooksys.entity.Mention;
import com.cooksys.entity.Twizzle;
import com.cooksys.repository.TwizzleRepo;

@Service
public class TwizzleService {
	
private TwizzleRepo twizzleRepo;
	
	public TwizzleService(TwizzleRepo twizzleRepo) {
		this.twizzleRepo = twizzleRepo;
		
		
	}
	public Twizzle get(Long id) {
		return twizzleRepo.getOne(id);
	}
	
	
	public Twizzle add(Twizzle twizzle) {
		twizzleRepo.save(twizzle);
		twizzleRepo.flush();
		return twizzleRepo.findOne(Example.of(twizzle));
	}
	
	public List<Twizzle> getAllTwizzles(){
		return twizzleRepo.findAll();
	}
	
	public List<Twizzle> getAllTwizzlesNonNullOrdered(){
		return twizzleRepo.getOrderedNondeletedTwizzles();
	}
	
	@Transactional
	public void deleteTwizzlerById(Long id){
		twizzleRepo.deleteTwizzlerById(id);
		twizzleRepo.flush();
	}
	
	@Transactional
	public void likeTwizzle(Long user_id, Long twizzle_id){
		twizzleRepo.setTwizzleLike(user_id, twizzle_id);
	}
	
	@Transactional
	public void twizzleMentions(List<Mention> mentionList){
		
		for(Mention mention : mentionList){
			twizzleRepo.setTwizzleMention(mention.getUser_name(),mention.getTwizzle_id());
		}
	}
	
	public List<String> getMentionByTwizzleId(Long twizzle_id){
		return twizzleRepo.getMentionByTwizzleId(twizzle_id);
	}
	
	public List<String> getUserLikesByTwizzleId(Long twizzle_id){
		return getUserLikesByTwizzleId(twizzle_id);
	}
	
	public List<Twizzle> getTwizzleReposts(Long twizzle_id){
		return twizzleRepo.getRepostsByTwizzleId(twizzle_id);
	}
	
	public List<Twizzle> getTwizzleReplies(Long twizzle_id){
		return twizzleRepo.getReplyByTwizzleId(twizzle_id);
	}
	
	public List<String> getHashtagsByTwizzleId(Long twizzle_id){
		return twizzleRepo.getHashtagsByTwizzleId(twizzle_id);
	}
	
	
	public List<Twizzle> getAllTwizzlesByHashtag(String hashtag)  {
		//ExampleMatcher matcher = ExampleMatcher.matching()
				//.withIgnoreCase(hashtag)
				//.
				//.withMatcher("label", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
		
		List<Hashtag> hashtags = new ArrayList<Hashtag>();
		
		Twizzle twizzle = new Twizzle();
		twizzle.setHashtagList(hashtags);
		hashtags.add(new Hashtag(hashtag));
		Example<Twizzle> example = Example.of(twizzle);

		return twizzleRepo.findAll(example);
	}
}
