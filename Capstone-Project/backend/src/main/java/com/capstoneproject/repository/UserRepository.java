package com.capstoneproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findOneByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
}
