package com.localapp.auth.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localapp.auth.login.dto.TravelAgentProfileDto;
import com.localapp.auth.login.entities.Role;
import com.localapp.auth.login.entities.TravelAgentProfile;
import com.localapp.auth.login.enums.RoleTypeEnum;
import com.localapp.auth.login.repositories.TravelAgentProfileRepository;
import com.localapp.auth.login.util.DateConverter;
import com.localapp.auth.login.util.HashGenerator;

@Service
public class TravelAgentProfileService {
	@Autowired
	TravelAgentProfileRepository agentProfileRepository;

	public void saveAgentProfile(TravelAgentProfileDto agentProfileDto) {
		System.out.println(agentProfileDto.toString());
		TravelAgentProfile agentProfile = convertAgentProfileDtoToObj(agentProfileDto);
		agentProfileRepository.save(agentProfile);
	}

	public TravelAgentProfile getAgentProfileByMobileNo(long mobileno) {
		String encryptedMobileNo = HashGenerator.generateHashValueForMobileNo(mobileno);
		return agentProfileRepository.getReferenceById(encryptedMobileNo);
	}

	private TravelAgentProfile convertAgentProfileDtoToObj(TravelAgentProfileDto agentProfileDto) {
		TravelAgentProfile agentProfile = new TravelAgentProfile();
		agentProfile.setEmail_hash(agentProfileDto.getEmail_hash());
		agentProfile.setRole(new Role(RoleTypeEnum.TRAVEL_AGENT.getRoleid(), RoleTypeEnum.TRAVEL_AGENT.getRole_name()));
		agentProfile.setIsactive(agentProfileDto.isIsactive());
		agentProfile.setMobileno_hash(agentProfileDto.getMobileno_hash());
		agentProfile.setCreated_date(DateConverter.convertLong(Long.valueOf(agentProfileDto.getCreated_date())));
		agentProfile.setUpdated_date(DateConverter.convertLong(Long.valueOf(agentProfileDto.getCreated_date())));
		return agentProfile;
	}
}
