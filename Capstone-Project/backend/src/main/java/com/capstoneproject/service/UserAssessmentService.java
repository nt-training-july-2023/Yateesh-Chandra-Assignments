package com.capstoneproject.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstoneproject.models.Question;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserAssessment;
import com.capstoneproject.repository.UserAssessmentRepository;
import com.capstoneproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * This is service class for User Assessment.
 */
@Service
public class UserAssessmentService {
    /**
     * This variable is created to perform operations for UserAssessment
     * Repository.
     */
    @Autowired
    private UserAssessmentRepository userAssessmentRepository;
    /**
     * This variable is created to perform operations for User Repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to get the User Assessments by entering User ID.
     * @param user The user whose assessment is required.
     * @return a List of user assessment.
     */
    private List<UserAssessment> getUserAssessmentsForUser(final User user) {
        List<UserAssessment> userAssessments = userAssessmentRepository
                .findByUser(user);
        for (UserAssessment userAssessment : userAssessments) {
            processUserAssessment(userAssessment);
        }
        return userAssessments;
    }

    /**
     * This is method written to update the correct answer.
     * @param userAssessment is of UserAssessment.
     */
    private void processUserAssessment(final UserAssessment userAssessment) {
        Question assessmentQuestion = userAssessment.getQuestion();
//        assessmentQuestion.updateCorrectAnswer();
//        String correctAnswer = assessmentQuestion.getCorrectAnswer();
//        userAssessment.setCorrectAnswer(correctAnswer);
        if (userAssessment.getChosenOption()
                .equals(assessmentQuestion.getCorrectOption())) {
            userAssessment.setCorrect(true);
        } else {
            userAssessment.setCorrect(false);
        }
    }

    /**
     * This method is used to get the score.
     * @param userAssessments - of List.
     * @return the score for the user Assessment.
     */
    public final int calculateScore(
            final List<UserAssessment> userAssessments) {
        int score = 0;
        for (UserAssessment assessment : userAssessments) {
            if (assessment.isCorrect()) {
                score++;
            }
        }
        return score;
    }

    /**
     * This method gets the user assessment when Id is given as input.
     * @param userAssessmentId is considered.
     * @return the User Assessment of a particular User ID.
     */
    public final UserAssessment getUserAssessmentById(
            final Long userAssessmentId) {
        UserAssessment userAssessment = userAssessmentRepository
                .findById(userAssessmentId).orElse(null);
        if (userAssessment != null) {
            processUserAssessment(userAssessment);
        }
        return userAssessment;
    }

    /**
     * Method written to get User Assessment by user.
     * @param userId taken.
     * @return the list of UserAssessments provided their User Id.
     */
    public final List<UserAssessment> getUserAssessmentsByUser(
            final Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return getUserAssessmentsForUser(user.get());
        }
        return Collections.emptyList();
    }

    /**
     * Method written to get the User Assessments By providing Question.
     * @param question is of Question Class.
     * @return the List of UserAssessments for a particular Question ID.
     */
    public final List<UserAssessment> getUserAssessmentByQuestion(
            final Question question) {
        List<UserAssessment> userAssessments = userAssessmentRepository
                .findByQuestion(question);
        for (UserAssessment userAssessment : userAssessments) {
            processUserAssessment(userAssessment);
        }
        return userAssessments;
    }

    /**
     * gets the user Assessments when the user and question details are given.
     * @param user     - User type.
     * @param question - Question type.
     * @return the user associated the User and Question.
     */
    public final List<UserAssessment> getUserAssessmentByUserAndQuestion(
            final User user, final Question question) {
        return userAssessmentRepository.findByUserAndQuestion(user, question);
    }

    /**
     * Add the User Assessments .
     * @param userAssessment is considered.
     * @return the User Assessment is added.
     */
    public final UserAssessment addUserAssessment(
            final UserAssessment userAssessment) {
//        Question question = userAssessment.getQuestion();
//        String correctAnswer = question.getCorrectAnswer();
//        userAssessment.setCorrectAnswer(correctAnswer);
        return userAssessmentRepository.save(userAssessment);
    }

    /**
     * deletes the User Assessment when Id is provided.
     * @param userAssessmentId - is of final.
     */
    public final void deleteAssessmentById(final Long userAssessmentId) {
        userAssessmentRepository.deleteById(userAssessmentId);
    }

    /**
     * This updates the Assessment based on the given parameters.
     * @param userAssessmentId  - with Long Data type.
     * @param updatedAssessment - with UserAssessment Type.
     * @return the updated User Assessment.
     */
    public final UserAssessment updateAssessment(final Long userAssessmentId,
            final UserAssessment updatedAssessment) {
        UserAssessment existingAssessment = userAssessmentRepository
                .findById(userAssessmentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User Assessment not found..!"));
        existingAssessment.setChosenOption(updatedAssessment.getChosenOption());
        existingAssessment.setScore(updatedAssessment.getScore());
        return userAssessmentRepository.save(existingAssessment);
    }
}
