package com.cooksys.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cooksys.entity.Hashtag;
import com.cooksys.entity.Mention;
import com.cooksys.entity.Twizzle;

public interface TwizzleRepo extends JpaRepository<Twizzle, Long> {
	
	@Query(value="SELECT * from twizzler.twizzle where deleted=false order by added desc", nativeQuery = true)
	public List<Twizzle> getOrderedNondeletedTwizzles();
	
	@Modifying
	@Query(value="update twizzler.twizzle set deleted=true where id=?", nativeQuery = true)
	public void deleteTwizzlerById(Long id);
	
	@Modifying
	@Query(value="insert into twizzler.likes (user_id,twizzle_id) values (?, ?)", nativeQuery = true)
	public void setTwizzleLike(Long user_id, Long twizzle_id);
	
	@Modifying
	@Query(value="insert into twizzler.mention (user_name,twizzle_id) values (?, ?)", nativeQuery = true)
	public void setTwizzleMention(String user_name, Long twizzle_id);
	
	
			
	@Query(value="select * from twizzler.twizzle where user_id in (select id from twizzler.user where username=?)", nativeQuery = true)
	public List<Twizzle> getTwizzlesByUser(String username);
	
	@Query(value="select * from twizzler.twizzle where id in (select twizzle_id from twizzler.mention where user_name=?)", nativeQuery = true)
	public List<Twizzle> getMentionByUserName(String username);
	
	@Query(value="select user_name from twizzler.mention where twizzle_id=?", nativeQuery = true)
	public List<String> getMentionByTwizzleId(Long twizzle_id);
	
	@Query(value="select * from twizzler.user where id in (select user_id from twizzler.likes where twizzle_id=?)", nativeQuery = true)
	public List<String> getUserLikesByTwizzleId(Long twizzle_id);
	
	@Query(value="select * from twizzler.twizzle where repost_of_id=?", nativeQuery = true)
	public List<Twizzle> getRepostsByTwizzleId(Long twizzle_id);
	
	@Query(value="select * from twizzler.twizzle where in_reply_to_id=?", nativeQuery = true)
	public List<Twizzle> getReplyByTwizzleId(Long twizzle_id);
	 
	@Query(value="select label from twizzler.hashtag where id in (select hashtag_list_id from twizzler.twizzle_hashtag_list where twizzle_id=?)", nativeQuery = true)
	public List<String> getHashtagsByTwizzleId(Long twizzle_id);
}
