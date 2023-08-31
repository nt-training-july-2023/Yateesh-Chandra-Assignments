package com.capstoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.Category;

/**
 * This interface contains the JPA Repository.
 */
@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {
}