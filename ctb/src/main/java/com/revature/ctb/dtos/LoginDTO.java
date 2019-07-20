package com.revature.ctb.dtos;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

	@NotEmpty(message = "Username is required") // Validating null values
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
