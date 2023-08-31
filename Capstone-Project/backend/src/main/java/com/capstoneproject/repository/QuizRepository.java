package com.capstoneproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;

/**
 * This interface contains the JPA Repository.
 */
@EnableJpaRepositories
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    /**
     * Find Category by Id.
     *
     * @param category The category to search for.
     * @return A category if exists.
     */
    List<Quiz> findByCategory(Category category);
}
