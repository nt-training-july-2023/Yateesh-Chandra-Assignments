package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void testGettersAndSetters() {
        Category category = new Category();
        category.setCategoryId(12L);
        category.setCategoryName("React");
        category.setDescription("This is React Category..!");
        
        assertEquals(12L, category.getCategoryId());
        assertEquals("React", category.getCategoryName());
        assertEquals("This is React Category..!", category.getDescription());
    }
    
    @Test
    public void testQuizListGetterAndSetter() {
        Category category = new Category();
        Quiz quiz1 = new Quiz();
        Quiz quiz2 = new Quiz();
        
        category.getQuiz().add(quiz1);
        category.getQuiz().add(quiz2);
        assertEquals(0, category.getQuiz().size());
        assertFalse(category.getQuiz().contains(quiz1));
        assertFalse(category.getQuiz().contains(quiz2));
    }

    @Test
    public void testCategoryConstructor() {
        Long categoryId = 12L;
        String categoryName = "React";
        String description = "This is React";
        Category category = new Category(categoryId,categoryName,description);
        
        assertEquals(categoryId, category.getCategoryId());
        assertEquals(categoryName, category.getCategoryName());
        assertEquals(description, category.getDescription());
        assertNotNull(category.getQuiz());
        assertTrue(category.getQuiz().isEmpty());
    }
    
    @Test
    public void testCategoryAllArgsConstructor() {
        Long categoryId = 12L;
        String categoryName = "React";
        String description = "This is React";
        Category category = new Category(categoryId,categoryName,description);
        
        assertEquals(categoryId, category.getCategoryId());
        assertEquals(categoryName, category.getCategoryName());
        assertEquals(description, category.getDescription());
        assertNotNull(category.getQuiz());
        assertTrue(category.getQuiz().isEmpty());
    }
}
