package com.cooksys.service;

import org.springframework.stereotype.Service;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Profile;
import com.cooksys.repository.ProfileRepo;

@Service
public class ProfileService {

private ProfileRepo profileRepo;
	
	public ProfileService(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
		
		
	}
	public Profile get(Long id) {
		return profileRepo.getOne(id);
	}

	public Profile add(Profile profile) {
		return profileRepo.saveAndFlush(profile);
	}
	
	public Profile update(Profile profile){
		Profile oldProfile = profileRepo.getOne(profile.getId());
		oldProfile.setEmail(profile.getEmail());
		oldProfile.setFirst_name(profile.getFirst_name());
		oldProfile.setLast_name(profile.getLast_name());
		oldProfile.setPhone(profile.getPhone());
		
		return profileRepo.save(oldProfile);
		
	}
}
