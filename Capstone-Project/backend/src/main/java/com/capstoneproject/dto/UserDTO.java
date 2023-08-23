package com.capstoneproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private long Id;
	private String name;
	private String email;
	private String password;
	private String userRole;
	private String phoneNumber;
}
