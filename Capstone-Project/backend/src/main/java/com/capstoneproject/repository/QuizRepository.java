package com.capstoneproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
    @Query("select quiz from Quiz as quiz where quiz.category.categoryId = :categoryId")
    List<Quiz> getQuizByCategoryId(Long categoryId);

    @Query("select q from Quiz as q where q.quizName = :quizName")
    Optional<Quiz> getQuizByName(String quizName);
}
