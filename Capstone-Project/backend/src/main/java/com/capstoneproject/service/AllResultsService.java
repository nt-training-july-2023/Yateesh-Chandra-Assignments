package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.repository.AllResultsRepository;

/**
 * This is the Service class for the All Results.
 */
@Service
public class AllResultsService {

    /**
     * This autowires the AllResults Repository.
     */
    @Autowired
    private AllResultsRepository allResultsRepository;

    /**
     * This is the getter method for All Results.
     * @return the list of All Results.
     */
    public final List<AllResultsDTO> getAllResults() {
        List<AllResults> allResults = allResultsRepository.findAll();
        return allResults.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    /**
     * This method converts the Entity to DTO.
     * @param allResults of AllResults.
     * @return the converted DTO.
     */
    private AllResultsDTO convertModelToDTO(final AllResults allResults) {
        AllResultsDTO allResultsDto = new AllResultsDTO();
        allResultsDto.setResultId(allResults.getResultId());
        allResultsDto.setUserId(allResults.getUserId());
        allResultsDto.setEmail(allResults.getEmail());
        allResultsDto.setUserName(allResults.getUserName());
        allResultsDto.setCategoryName(allResults.getCategoryName());
        allResultsDto.setQuizName(allResults.getQuizName());
        allResultsDto.setNumOfQuestions(allResults.getNumOfQuestions());
        allResultsDto.setNumOfQuestionsAnswered(
                allResults.getNumOfQuestionsAnswered());
        allResultsDto.setTotalMarks(allResults.getTotalMarks());
        allResultsDto.setMarksScored(allResults.getMarksScored());
        allResultsDto.setTimeStamp(allResults.getTimeStamp());
        return allResultsDto;
    }

    /**
     * This method gets the Results by user Id and Quiz Name.
     * @param userId - Long type.
     * @param quizName - String type.
     * @return the specific Result.
     */
    public final Optional<AllResultsDTO> getResultsByUserIdAndQuizName(
            final Long userId, final String quizName) {
        Optional<AllResults> allResults = allResultsRepository
                .getResultsByUserIdAndQuizName(userId, quizName);
        AllResults results = allResults.get();
        AllResultsDTO resultsDto = new AllResultsDTO();
        resultsDto.setResultId(results.getResultId());
        resultsDto.setUserId(userId);
        resultsDto.setUserName(results.getUserName());
        resultsDto.setCategoryName(results.getCategoryName());
        resultsDto.setQuizName(quizName);
        resultsDto.setNumOfQuestions(results.getNumOfQuestions());
        resultsDto
              .setNumOfQuestionsAnswered(results.getNumOfQuestionsAnswered());
        resultsDto.setTotalMarks(results.getTotalMarks());
        resultsDto.setMarksScored(results.getMarksScored());
        resultsDto.setTimeStamp(results.getTimeStamp());
        return Optional.of(resultsDto);
    }

    /**
     * This method gets the Result by Email.
     * @param userId is passed.
     * @return the List of Results for that email.
     */
    public final List<AllResultsDTO> getResultsByUserId(final Long userId) {
        List<AllResults> allResults = allResultsRepository
                .getResultsByUserId(userId);
        return allResults.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }
}
