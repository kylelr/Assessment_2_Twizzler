package com.cooksys.service;

import com.cooksys.entity.Credentials;
import com.cooksys.repository.CredentialsRepo;

public class CredentialsService {

private CredentialsRepo credentialsRepo;
	
	public CredentialsService(CredentialsRepo credentialsRepo) {
		this.credentialsRepo = credentialsRepo;
		
		
	}
	public Credentials get(Long id) {
		return credentialsRepo.getOne(id);
	}

	public void add(Credentials credentials) {
		credentialsRepo.saveAndFlush(credentials);
	}
}
