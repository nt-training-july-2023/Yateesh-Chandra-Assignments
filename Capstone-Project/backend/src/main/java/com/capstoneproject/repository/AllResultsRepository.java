package com.capstoneproject.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.AllResults;

@EnableJpaRepositories
public interface AllResultsRepository extends JpaRepository<AllResults, Long>{

    @Query("select result from AllResults as result where"
            + " result.userId = :userId and result.quizName = :quizName")
    Optional<AllResults> getResultsByUserIdAndQuizName(Long userId, String quizName);

    @Query("select result from AllResults as result where"
            + " result.email = :email")
    List<AllResults> getResultsByEmail(String email);
}
