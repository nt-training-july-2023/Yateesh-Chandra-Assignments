package com.capstoneproject.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.repository.AllResultsRepository;

@Service
public class AllResultsService {

    @Autowired
    private AllResultsRepository allResultsRepository;

    public List<AllResultsDTO> getAllResults(){
        List<AllResults> allResults = allResultsRepository.findAll();
        return allResults.stream().map(this::convertModelToDTO).collect(Collectors.toList());
    }

    private AllResultsDTO convertModelToDTO(AllResults allResults){
        AllResultsDTO allResultsDto = new AllResultsDTO();
        allResultsDto.setResultId(allResults.getResultId());
        allResultsDto.setUserId(allResults.getUserId());
        allResultsDto.setEmail(allResults.getEmail());
        allResultsDto.setUserName(allResults.getUserName());
        allResultsDto.setCategoryName(allResults.getCategoryName());
        allResultsDto.setQuizName(allResults.getQuizName());
        allResultsDto.setNumOfQuestions(allResults.getNumOfQuestions());
        allResultsDto.setNumOfQuestionsAnswered(allResults.getNumOfQuestionsAnswered());
        allResultsDto.setTotalMarks(allResults.getTotalMarks());
        allResultsDto.setMarksScored(allResults.getMarksScored());
        allResultsDto.setTimeStamp(allResults.getTimeStamp());
        return allResultsDto;
    }

    public final Optional<AllResultsDTO> getResultsByUserIdAndQuizName(Long userId, String quizName){
        Optional<AllResults> allResults = allResultsRepository.getResultsByUserIdAndQuizName(userId, quizName);
        AllResults results = allResults.get();
        AllResultsDTO resultsDto = new AllResultsDTO();
        resultsDto.setResultId(results.getResultId());
        resultsDto.setUserId(userId);
        resultsDto.setUserName(results.getUserName());
        resultsDto.setCategoryName(results.getCategoryName());
        resultsDto.setQuizName(quizName);
        resultsDto.setNumOfQuestions(results.getNumOfQuestions());
        resultsDto.setNumOfQuestionsAnswered(results.getNumOfQuestionsAnswered());
        resultsDto.setTotalMarks(results.getTotalMarks());
        resultsDto.setMarksScored(results.getMarksScored());
        resultsDto.setTimeStamp(results.getTimeStamp());
        return Optional.of(resultsDto);
    }

    public final List<AllResultsDTO> getResultsByEmail(String email){
        List<AllResults> allResults = allResultsRepository.getResultsByEmail(email);
        return allResults.stream().map(this::convertModelToDTO).collect(Collectors.toList());
        
    }
}
