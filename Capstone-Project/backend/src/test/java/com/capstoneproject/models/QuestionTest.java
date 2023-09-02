package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void testQuestionGetterAndSetter3() {
        Question question = new Question();
        question.setQuestionId(10L);
        question.setQuestionTitle("What is not the primitive Data Type?");
        question.setOption1("int");
        question.setOption2("float");
        question.setOption3("List");
        question.setOption4("boolean");
        question.setCorrectOption("option3");
        question.setCorrectAnswer("List");
        
        assertEquals(10L, question.getQuestionId());
        assertEquals("What is not the primitive Data Type?", question.getQuestionTitle());
        assertEquals("int", question.getOption1());
        assertEquals("float", question.getOption2());
        assertEquals("List", question.getOption3());
        assertEquals("boolean", question.getOption4());
        assertEquals("option3", question.getCorrectOption());
        assertEquals("List", question.getCorrectAnswer());
    }
    
    @Test
    void testQuestionGetterAndSetter1() {
        Question question = new Question();
        question.setQuestionId(11L);
        question.setQuestionTitle("What is not the primitive Data Type?");
        question.setOption1("int");
        question.setOption2("List");
        question.setOption3("String");
        question.setOption4("Long");
        question.setCorrectOption("option1");
        question.setCorrectAnswer("int");
        
        assertEquals(11L, question.getQuestionId());
        assertEquals("What is not the primitive Data Type?", question.getQuestionTitle());
        assertEquals("int", question.getOption1());
        assertEquals("List", question.getOption2());
        assertEquals("String", question.getOption3());
        assertEquals("Long", question.getOption4());
        assertEquals("option1", question.getCorrectOption());
        assertEquals("int", question.getCorrectAnswer());
    }
    @Test
    void testQuestionGetterAndSetter4() {
        Question question = new Question();
        question.setQuestionId(13L);
        question.setQuestionTitle("What is the size of Boolean Data Type?");
        question.setOption1("4bit");
        question.setOption2("16bit");
        question.setOption3("2bit");
        question.setOption4("1bit");
        question.setCorrectOption("option4");
        question.setCorrectAnswer("1bit");
        
        assertEquals(13L, question.getQuestionId());
        assertEquals("What is the size of Boolean Data Type?", question.getQuestionTitle());
        assertEquals("4bit", question.getOption1());
        assertEquals("16bit", question.getOption2());
        assertEquals("2bit", question.getOption3());
        assertEquals("1bit", question.getOption4());
        assertEquals("option4", question.getCorrectOption());
        assertEquals("1bit", question.getCorrectAnswer());
    }
    
    @Test
    void testQuestionGetterAndSetter2() {
        Question question = new Question();
        question.setQuestionId(12L);
        question.setQuestionTitle("LIFO is followed by which Data Structure?");
        question.setOption1("Queue");
        question.setOption2("Stack");
        question.setOption3("Array");
        question.setOption4("Linked List");
        question.setCorrectOption("option2");
        question.setCorrectAnswer("Stack");
        
        assertEquals(12L, question.getQuestionId());
        assertEquals("LIFO is followed by which Data Structure?", question.getQuestionTitle());
        assertEquals("Queue", question.getOption1());
        assertEquals("Stack", question.getOption2());
        assertEquals("Array", question.getOption3());
        assertEquals("Linked List", question.getOption4());
        assertEquals("option2", question.getCorrectOption());
        assertEquals("Stack", question.getCorrectAnswer());
    }
    
    @Test
    public void testQuestionAllArgsConstructor(){
        Long questionId = 11L;
        String questionTitle = "What is the size of Boolean Data Type?";
        String option1 = "4bit";
        String option2 = "16bit";
        String option3 = "2bit";
        String option4 = "1bit";
        String correctOption = "option4";
        String correctAnswer = "1bit";
        Quiz quiz = new Quiz();
        Question question = new Question(questionId, questionTitle, option1, option2, option3, option4,correctOption, correctAnswer, quiz);
        
        assertEquals(questionId, question.getQuestionId());
        assertEquals(questionTitle, question.getQuestionTitle());
        assertEquals(option1, question.getOption1());
        assertEquals(option2, question.getOption2());
        assertEquals(option3, question.getOption3());
        assertEquals(option4, question.getOption4());
        assertEquals(correctOption, question.getCorrectOption());
        assertEquals(correctAnswer, question.getCorrectAnswer());
        assertEquals(quiz, question.getQuiz());
    }
}
