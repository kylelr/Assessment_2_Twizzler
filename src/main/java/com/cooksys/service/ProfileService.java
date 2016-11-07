package com.cooksys.service;

import com.cooksys.entity.Profile;
import com.cooksys.repository.ProfileRepo;

public class ProfileService {

private ProfileRepo profileRepo;
	
	public ProfileService(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
		
		
	}
	public Profile get(Long id) {
		return profileRepo.getOne(id);
	}

	public void add(Profile profile) {
	profileRepo.saveAndFlush(profile);
	}
}
