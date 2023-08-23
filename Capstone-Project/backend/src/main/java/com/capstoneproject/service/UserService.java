package com.capstoneproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstoneproject.controller.LoginResponse;
import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String addUser(UserDTO userDTO) {
		
		if (userDTO.getUserRole() == null) {
            userDTO.setUserRole("USER");
        }
		
		User user = new User(
				userDTO.getId(),
				userDTO.getName(),
				userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()),
				userDTO.getUserRole(),
				userDTO.getPhoneNumber()
				);
		userRepo.save(user);
		return user.getName();
	}
	
	UserDTO userDTO;
	
	public LoginResponse loginUser(LoginDTO loginDTO) {
		String msg = "";
		User user1 = userRepo.findByEmail(loginDTO.getEmail());
		
		if(user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			
			if(isPwdRight){
				Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
			
			
			if(user.isPresent()) {
				return new LoginResponse(msg + "Login Successful..!", true, user.get().getUserRole());
			}
			
			else {
				return new LoginResponse(msg + "Login Failed", false);
			}
		}
			else {
				return new LoginResponse(msg + "Passwords did not match", false);
			}
		}
		
		else {
			return new LoginResponse(msg + "Email does not exist.!", false);
		}
	}
	
}
