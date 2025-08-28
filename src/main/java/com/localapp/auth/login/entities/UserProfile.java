package com.localapp.auth.login.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	private String mobileNo_hash;
	private String email_hash;
	private boolean active;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;
	private LocalDate created_date;
	private LocalDate updated_date;
}
