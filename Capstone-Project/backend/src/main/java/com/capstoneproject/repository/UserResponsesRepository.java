package com.capstoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.UserResponses;

/**
 * This is The Repository for User Responses.
 */
@EnableJpaRepositories
public interface UserResponsesRepository
        extends JpaRepository<UserResponses, Long> {

    /**
     * This method is used to find the User Responses By User and Quiz.
     * @param userId - Long type.
     * @param quizId - Long Type.
     * @return the Responses.
     */
    @Query("select response from UserResponses as response where "
            + "response.users.userId = :userId and"
            + " response.quiz.quizId = :quizId")
    UserResponses findResponsesByUsersAndQuiz(Long userId, Long quizId);
}
