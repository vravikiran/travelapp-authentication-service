package com.localapp.auth.login.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.localapp.auth.login.dto.AuthRequest;
import com.localapp.auth.login.dto.EmailAuthRequest;
import com.localapp.auth.login.services.MessageService;
import com.localapp.auth.login.services.TravelAgentProfileService;
import com.localapp.auth.login.util.JwtHelper;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	TravelAgentProfileService agentProfileService;
	@Autowired
	MessageService messageService;
	@Autowired
	JwtHelper jwtHelper;

	@GetMapping("/mobileotp")
	public ResponseEntity<String> generateMobileOtp(@RequestParam long mobileno) {
		messageService.generateMobileOtp(mobileno);
		return ResponseEntity.ok("otp generated successfully");
	}

	@GetMapping("/emailotp")
	public ResponseEntity<String> generateEmailOtp(@RequestParam String email)
			throws UnsupportedEncodingException, MessagingException {
		messageService.generateEmailOtp(email);
		return ResponseEntity.ok("otp sent to email successfully");
	}

	@PostMapping("/verify/mobileotp")
	public ResponseEntity<String> validateMobileOtp(@RequestBody AuthRequest authRequest) {
		boolean isValidOtp = messageService.validateMobileOtp(authRequest);
		String token = "";
		if (isValidOtp) {
			token = jwtHelper.generateTokenByMobileNo(Long.toString(authRequest.getMobileno()));
			return ResponseEntity.ok(token);
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid otp");
	}

	@PostMapping("/verify/emailotp")
	public ResponseEntity<String> validateEmailOtp(@RequestBody EmailAuthRequest authRequest) {
		boolean isValidOtp = messageService.validateEmailOtp(authRequest);
		String token = "";
		if (isValidOtp) {
			token = jwtHelper.generateTokenByEmail(authRequest.getEmail());
			return ResponseEntity.ok(token);
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid otp");
	}
}
