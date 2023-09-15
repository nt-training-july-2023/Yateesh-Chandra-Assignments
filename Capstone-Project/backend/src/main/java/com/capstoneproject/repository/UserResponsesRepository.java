package com.capstoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.UserResponses;

@EnableJpaRepositories
public interface UserResponsesRepository
        extends JpaRepository<UserResponses, Long> {

    @Query("select response from UserResponses as response where "
            + "response.users.userId = :userId and response.quiz.quizId = :quizId")
    UserResponses findResponsesByUsersAndQuiz(Long userId, Long quizId);
}
