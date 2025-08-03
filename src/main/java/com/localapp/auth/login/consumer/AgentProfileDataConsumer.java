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
import com.localapp.auth.login.dto.TravelAgentProfileDto;
import com.localapp.auth.login.services.TravelAgentProfileService;

@Service
public class AgentProfileDataConsumer {
	@Autowired
	TravelAgentProfileService agentProfileService;

	@KafkaListener(topics = {
			"ps_.localapp_user_mgmt.travel_agent_profile" }, containerFactory = "kafkaListenerStringFactory", concurrency = "4", groupId = "travel-agent-group")
	public void consumeMessages(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
			throws JsonMappingException, JsonProcessingException {
		TravelAgentProfileDto agentProfileDto = new ObjectMapper().readValue(message, TravelAgentProfileDto.class);
		agentProfileService.saveAgentProfile(agentProfileDto);
		System.out.println("received message" + message);
	}
}
