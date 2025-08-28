package com.localapp.auth.login.services;

import com.localapp.auth.login.util.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.localapp.auth.login.dto.UserProfileDto;
import com.localapp.auth.login.entities.Role;
import com.localapp.auth.login.entities.UserProfile;
import com.localapp.auth.login.enums.RoleTypeEnum;
import com.localapp.auth.login.repositories.UserProfileRepository;
import com.localapp.auth.login.util.DateConverter;

@Service
public class UserProfileService {
	@Autowired
	UserProfileRepository userProfileRepository;

	public void saveUserProfile(UserProfileDto userProfileDto) {
		UserProfile userProfile = convertUserProfileDtoToObj(userProfileDto);
		userProfileRepository.save(userProfile);
	}

    public UserProfile getUserProfileByMobileNo(long mobileNo) {
        return userProfileRepository.getReferenceById(HashGenerator.generateHashValueForMobileNo(mobileNo));
    }

    public UserProfile getUserByEmail(String email) {
        return userProfileRepository.findByEmail(HashGenerator.generateHashValueForEmail(email));
    }

	private UserProfile convertUserProfileDtoToObj(UserProfileDto userProfileDto) {
		UserProfile userProfile = new UserProfile();
		userProfile.setMobileNo_hash(userProfileDto.getMobileno_hash());
		userProfile.setEmail_hash(userProfileDto.getEmail_hash());
		userProfile.setActive(userProfileDto.isIsactive());
		userProfile.setRole(new Role(RoleTypeEnum.CUSTOMER.getRoleid(), RoleTypeEnum.CUSTOMER.getRole_name()));
		userProfile.setCreated_date(DateConverter.convertLong(Long.parseLong(userProfileDto.getCreated_date())));
		userProfile.setUpdated_date(DateConverter.convertLong(Long.parseLong(userProfileDto.getCreated_date())));
		return userProfile;
	}
}