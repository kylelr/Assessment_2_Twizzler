package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

}
