package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class CategoryDTOTest {

    @Test
    public void testCategoryGetterAndSetter() {
        Long categoryId = 5L;
        String categoryName = "Java";
        String categoryDescription = "This is Category Name";
        
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(categoryId);
        categoryDto.setCategoryName(categoryName);
        categoryDto.setDescription(categoryDescription);
        
        assertEquals(categoryId, categoryDto.getCategoryId());
        assertEquals(categoryName, categoryDto.getCategoryName());
        assertEquals(categoryDescription, categoryDto.getDescription());
    }
    
    @Test
    public void testCategoryDTOToString() {
        CategoryDTO categoryDto = new CategoryDTO(5L, "Java", "This is Java");
        String expectedString = String.format("CategoryDTO(categoryId=%d, categoryName=%s, description=%s)", categoryDto.getCategoryId(), categoryDto.getCategoryName(),categoryDto.getDescription());
        assertEquals(expectedString, categoryDto.toString());
    }
    
    @Test
    public void testCategoryDTODefaultConstructor() {
        CategoryDTO categoryDto = new CategoryDTO();
        assertEquals(null, categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getCategoryName());
        assertEquals(null, categoryDto.getDescription());
    }
    
    @Test
    public void testCategoryDTOAllArgsConstructor() {
        Long categoryId = 5L;
        String categoryName = "Java";
        String description = "This is Category Name";
        
        CategoryDTO categoryDto = new CategoryDTO(categoryId, categoryName, description);
        assertEquals(categoryId, categoryDto.getCategoryId());
        assertEquals(categoryName, categoryDto.getCategoryName());
        assertEquals(description,  categoryDto.getDescription());
    }

}
