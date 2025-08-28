package com.localapp.auth.login.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private long mobileNo;
	private String otp;
}
