package com.localapp.auth.login.dto;

public class UserProfileDto {
	private String mobileno_hash;
	private String email_hash;
	private boolean isactive;
	private String __op;
	private int role_id;
	private String created_date;
	private String updated_date;

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

	public String get__op() {
		return __op;
	}

	public void set__op(String __op) {
		this.__op = __op;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public UserProfileDto() {
		super();
	}

	@Override
	public String toString() {
		return "UserProfileDto [mobileno_hash=" + mobileno_hash + ", email_hash=" + email_hash + ", isactive="
				+ isactive + ", __op=" + __op + ", role_id=" + role_id + ", created_date=" + created_date
				+ ", updated_date=" + updated_date + "]";
	}
}
