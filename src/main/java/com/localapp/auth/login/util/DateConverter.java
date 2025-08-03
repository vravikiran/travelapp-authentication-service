package com.localapp.auth.login.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateConverter {
	public static LocalDate convertLong(long timestampMillis) {
		LocalDate date = Instant.ofEpochMilli(Long.valueOf(timestampMillis)).atZone(ZoneId.systemDefault())
				.toLocalDate();
		return date;
	}
}
