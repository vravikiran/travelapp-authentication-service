package com.localapp.auth.login.services;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.localapp.auth.login.config.TwilioConfig;
import com.localapp.auth.login.dto.AuthRequest;
import com.localapp.auth.login.dto.EmailAuthRequest;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.mail.MessagingException;
import com.twilio.type.PhoneNumber;

@Service
public class MessageService {
	@Autowired
	CacheManager cacheManager;
	@Autowired
	EmailService emailService;
	@Autowired
	TwilioConfig twilioConfig;
    Logger logger = LoggerFactory.getLogger(MessageService.class);

	public String getRandomOtp() {
		return String.valueOf(new Random().nextInt(1000, 10000));
	}

	@Cacheable(value = "otpCache")
	public String generateMobileOtp(long mobileNo) {
		String generatedOtp = getRandomOtp();
		 PhoneNumber to = new PhoneNumber("+91" + mobileNo);
		String otpMessage = "Please find the OTP to login into Travel With Locals App: " + generatedOtp;
         System.out.println(otpMessage);
        //Message.creator(to, twilioConfig.getServiceId(), otpMessage).create();
		 logger.info("otp generated successfully for given mobile number :: {}" ,
		 mobileNo);

		return generatedOtp;
	}

	public boolean validateMobileOtp(AuthRequest authRequest) {
		boolean isValidOtp = false;
		if (cacheManager.getCache("otpCache") != null && cacheManager.getCache("otpCache").get(authRequest.getMobileNo()) != null) {
			String otpFromCache = (String) cacheManager.getCache("otpCache").get(authRequest.getMobileNo()).get();
			if (otpFromCache != null && otpFromCache.equals(authRequest.getOtp()))
				isValidOtp = true;
		}
		return isValidOtp;
	}

	@Cacheable(value="otpCache")
	public String generateEmailOtp(String email) throws UnsupportedEncodingException, MessagingException {
		String generatedOtp = getRandomOtp();
		emailService.sendEmail(email, "OTP to validate user", generatedOtp);
		return generatedOtp;
	}
	
	public boolean validateEmailOtp(EmailAuthRequest authRequest) {
		boolean isValidOtp = false;
		if(cacheManager.getCache("otpCache") != null && cacheManager.getCache("otpCache").get(authRequest.getEmail()) != null) {
			String otpFromCache = (String) cacheManager.getCache("otpCache").get(authRequest.getEmail()).get();
			if(otpFromCache != null && otpFromCache.equals(authRequest.getOtp())) {
				isValidOtp = true;
			}
		}
		return isValidOtp;
	}
}
