package com.localapp.auth.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.localapp.auth.login.dto.UserProfileDto;
import com.localapp.auth.login.entities.Role;
import com.localapp.auth.login.entities.UserInfoDetails;
import com.localapp.auth.login.entities.UserProfile;
import com.localapp.auth.login.enums.RoleTypeEnum;
import com.localapp.auth.login.repositories.UserProfileRepository;
import com.localapp.auth.login.util.DateConverter;
import com.localapp.auth.login.util.HashGenerator;

@Service
public class UserProfileService {
	@Autowired
	UserProfileRepository userProfileRepository;

	public UserProfile getUserProfileByMobileNo(long mobileno) {
		String encrptedMobileNo = HashGenerator.generateHashValueForMobileNo(mobileno);
		return userProfileRepository.getReferenceById(encrptedMobileNo);
	}

	public void saveUserProfile(UserProfileDto userProfileDto) {
		UserProfile userProfile = convertUserProfileDtoToObj(userProfileDto);
		userProfileRepository.save(userProfile);
	}

	private UserProfile convertUserProfileDtoToObj(UserProfileDto userProfileDto) {
		UserProfile userProfile = new UserProfile();
		userProfile.setMobileno_hash(userProfileDto.getMobileno_hash());
		userProfile.setEmail_hash(userProfileDto.getEmail_hash());
		userProfile.setIsactive(userProfileDto.isIsactive());
		userProfile.setRole(new Role(RoleTypeEnum.CUSTOMER.getRoleid(), RoleTypeEnum.CUSTOMER.getRole_name()));
		userProfile.setCreated_date(DateConverter.convertLong(Long.valueOf(userProfileDto.getCreated_date())));
		userProfile.setUpdated_date(DateConverter.convertLong(Long.valueOf(userProfileDto.getCreated_date())));
		return userProfile;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile userProfile = getUserProfileByMobileNo(Long.valueOf(username));
		if (userProfile != null)
			return new UserInfoDetails(userProfile, username);
		else {
			throw new UsernameNotFoundException("User not found with given mobileNo " + username);
		}
	}

	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		UserProfile userProfile = userProfileRepository.findByEmail(HashGenerator.generateHashValueForEmail(email));
		if (userProfile != null)
			return new UserInfoDetails(userProfile, email);
		else {
			throw new UsernameNotFoundException("User not found with given email " + email);
		}
	}
}
