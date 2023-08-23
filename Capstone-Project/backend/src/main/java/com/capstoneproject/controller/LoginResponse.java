package com.capstoneproject.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

	String message;
	Boolean status;
	String userRole;
	
	public LoginResponse(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
}
