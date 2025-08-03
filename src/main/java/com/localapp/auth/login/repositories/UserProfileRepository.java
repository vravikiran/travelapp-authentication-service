package com.localapp.auth.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.localapp.auth.login.entities.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	@Query(value = "select u from UserProfile u where u.email_hash= :email_hash")
	public UserProfile findByEmail(String email_hash);
}
