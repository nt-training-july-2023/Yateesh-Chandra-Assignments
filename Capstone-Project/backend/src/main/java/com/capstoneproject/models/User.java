package com.capstoneproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", length = 45)
	private long id;
	
	@Column(name = "user_name", length = 255)
	private String name;
	
	@Column(name = "user_email", unique = true, length = 255)
	private String email;
	
	@Column(name = "password", length = 255)
	private String password;
	
	@Column(name = "user_role", columnDefinition = "varchar(255) default 'USER'")
	private String userRole;
	
	@Column(name = "phone_number")
	private String phoneNumber;
}
