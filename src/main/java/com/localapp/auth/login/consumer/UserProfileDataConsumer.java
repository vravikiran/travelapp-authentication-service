package com.localapp.auth.login.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.localapp.auth.login.dto.UserProfileDto;
import com.localapp.auth.login.services.UserProfileService;

@Service
public class UserProfileDataConsumer {
	@Autowired
	UserProfileService userProfileService;

	@KafkaListener(topics = "ps_.localapp_user_mgmt.user_profile", groupId = "user-profile-group", containerFactory = "kafkaListenerStringFactory", concurrency = "4")
	public void consumeUserProfileMessages(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
			throws JsonMappingException, JsonProcessingException {
		UserProfileDto userProfileDto = new ObjectMapper().readValue(message, UserProfileDto.class);
		System.out.println(userProfileDto);
		userProfileService.saveUserProfile(userProfileDto);
		System.out.println("received message 2" + message);
	}
}
