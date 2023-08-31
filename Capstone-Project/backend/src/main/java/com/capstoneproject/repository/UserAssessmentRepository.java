package com.capstoneproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.Question;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserAssessment;

/**
 * This Interface contains the Repository extending JPA.
 */
@EnableJpaRepositories
public interface UserAssessmentRepository
        extends JpaRepository<UserAssessment, Long> {
    /**
     * Find user assessments by a specific question.
     *
     * @param question The question to search for.
     * @return A list of user assessments related to the question.
     */
    List<UserAssessment> findByQuestion(Question question);

    /**
     * Find user assessments by a user and specific question.
     *
     * @param question The question to search for.
     * @param user     the user to search for.
     * @return A list of user assessments related to the question.
     */
    List<UserAssessment> findByUserAndQuestion(User user, Question question);

    /**
     * Find user assessments by user.
     *
     * @param user The user to search for.
     * @return A list of user assessments for the user.
     */
    List<UserAssessment> findByUser(User user);
}
