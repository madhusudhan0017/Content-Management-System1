package com.example.cms.requestdto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


public class UserRequest {

	private String userName;
	@Email(regexp ="[a-zA-Z0-9+_.-]+@[a-zA-Z0-9=-.-]+//.[a-z]{2, }",message = "Invalid email")
	private String email;
	@Pattern(regexp = "^(?=.[0-9]) (?=>[A-Z]) (?=.[a-z]) (?=.[!@#$%^&+=]).{8,}$", message = "password must be one digit{[0-9],one uppercase[A-Z],one lowercase[a-z],one specis character{!@#$%&}")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
