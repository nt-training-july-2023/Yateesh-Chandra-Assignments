package com.capstoneproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;

@EnableJpaRepositories
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	List<Quiz> findByCategory(Category category);
}
