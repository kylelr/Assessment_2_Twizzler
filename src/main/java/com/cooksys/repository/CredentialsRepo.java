package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Credentials;

public interface CredentialsRepo extends JpaRepository<Credentials, Long>{

}
