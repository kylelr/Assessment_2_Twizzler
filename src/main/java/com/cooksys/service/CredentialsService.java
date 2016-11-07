package com.cooksys.service;

import org.springframework.stereotype.Service;

import com.cooksys.entity.Credentials;
import com.cooksys.repository.CredentialsRepo;

@Service
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
	
	public void update(Credentials credentials){
		Credentials oldCredentials = credentialsRepo.getOne(credentials.getId());
		oldCredentials.setUsername(credentials.getUsername());
		oldCredentials.setPassword(credentials.getPassword());
		credentialsRepo.save(oldCredentials);
	}
}
