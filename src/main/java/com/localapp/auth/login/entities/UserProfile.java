package com.localapp.auth.login.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	private String mobileno_hash;
	private String email_hash;
	private boolean isactive;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;
	private LocalDate created_date;
	private LocalDate updated_date;

	public String getMobileno_hash() {
		return mobileno_hash;
	}

	public void setMobileno_hash(String mobileno_hash) {
		this.mobileno_hash = mobileno_hash;
	}

	public String getEmail_hash() {
		return email_hash;
	}

	public void setEmail_hash(String email_hash) {
		this.email_hash = email_hash;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDate getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}

	public LocalDate getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(LocalDate updated_date) {
		this.updated_date = updated_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_date, email_hash, isactive, mobileno_hash, role, updated_date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		return Objects.equals(created_date, other.created_date) && Objects.equals(email_hash, other.email_hash)
				&& isactive == other.isactive && Objects.equals(mobileno_hash, other.mobileno_hash)
				&& Objects.equals(role, other.role) && Objects.equals(updated_date, other.updated_date);
	}

	public UserProfile() {
		super();
	}
}
