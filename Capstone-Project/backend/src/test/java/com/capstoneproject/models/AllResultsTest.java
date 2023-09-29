package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AllResultsTest {

    @Test
    void testGettersAndSetters() {
        Long resultId = 4L;
        Long userId = 5L;
        String email = "test@test.com";
        String userName = "test User";
        String categoryName = "Test Category";
        String quizName = "Test Quiz";
        int numOfQ = 4;
        int numOfQA = 2;
        int tot = 4;
        int mar = 2;
        String timeStamp = "2023-09-20";

        AllResults allResults = new AllResults();
        allResults.setResultId(resultId);
        allResults.setUserId(userId);
        allResults.setEmail(email);
        allResults.setUserName(userName);
        allResults.setCategoryName(categoryName);
        allResults.setQuizName(quizName);
        allResults.setNumOfQuestions(numOfQ);
        allResults.setNumOfQuestionsAnswered(numOfQA);
        allResults.setTotalMarks(tot);
        allResults.setMarksScored(mar);
        allResults.setTimeStamp(timeStamp);

        assertEquals(resultId, allResults.getResultId());
        assertEquals(userId, allResults.getUserId());
        assertEquals(email, allResults.getEmail());
        assertEquals(userName, allResults.getUserName());
        assertEquals(categoryName, allResults.getCategoryName());
        assertEquals(quizName, allResults.getQuizName());
        assertEquals(numOfQ, allResults.getNumOfQuestions());
        assertEquals(numOfQA, allResults.getNumOfQuestionsAnswered());
        assertEquals(tot, allResults.getTotalMarks());
        assertEquals(mar, allResults.getMarksScored());
        assertEquals(timeStamp, allResults.getTimeStamp());
    }

    @Test
    void testAllArgsConstructor() {
        Long resultId = 4L;
        Long userId = 5L;
        String email = "test@test.com";
        String userName = "test User";
        String categoryName = "Test Category";
        String quizName = "Test Quiz";
        int numOfQ = 4;
        int numOfQA = 2;
        int tot = 4;
        int mar = 2;
        String timeStamp = "2023-09-20";

        AllResults allResults = new AllResults(resultId, userId, email, userName, categoryName,
               quizName, numOfQ, numOfQA, tot, mar, timeStamp);

        assertEquals(resultId, allResults.getResultId());
        assertEquals(userId, allResults.getUserId());
        assertEquals(email, allResults.getEmail());
        assertEquals(userName, allResults.getUserName());
        assertEquals(categoryName, allResults.getCategoryName());
        assertEquals(quizName, allResults.getQuizName());
        assertEquals(numOfQ, allResults.getNumOfQuestions());
        assertEquals(numOfQA, allResults.getNumOfQuestionsAnswered());
        assertEquals(tot, allResults.getTotalMarks());
        assertEquals(mar, allResults.getMarksScored());
        assertEquals(timeStamp, allResults.getTimeStamp());
    }

}
