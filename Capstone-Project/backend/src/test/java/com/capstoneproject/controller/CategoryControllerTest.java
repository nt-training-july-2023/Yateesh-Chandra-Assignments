package com.capstoneproject.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.models.Category;
import com.capstoneproject.service.CategoryService;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;
    
    @InjectMocks
    private CategoryController categoryController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO(1L, "Category1", "Category Description 1"));
        categories.add(new CategoryDTO(2L, "Category2", "Category Description 2"));
        when(categoryService.getAllCategories()).thenReturn(categories);
        ResponseEntity<Object> response = categoryController.getAllCategories();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CategoryDTO> responseCategories = (List<CategoryDTO>) response.getBody();
        assertNotNull(responseCategories);
        assertEquals(2, responseCategories.size());
        assertEquals("Category1", responseCategories.get(0).getCategoryName());
        assertEquals("Category Description 2", responseCategories.get(1).getDescription());
    }

    @Test
    public void testAddCategory() {
        CategoryDTO category = new CategoryDTO(null, "New Category", "New Description");
        when(categoryService.addCategory(category)).thenReturn(category);
        ResponseEntity<Object> response = categoryController.addCategory(category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CategoryDTO responseCategory = (CategoryDTO) response.getBody();
        assertNotNull(responseCategory);
        assertEquals("New Category", responseCategory.getCategoryName());
        assertEquals("New Description", responseCategory.getDescription());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 4L;
        CategoryDTO category = new CategoryDTO(categoryId, "Category1", "Description1");
        
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        
        ResponseEntity<Object> response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CategoryDTO responseCategory = (CategoryDTO) response.getBody();
        assertNotNull(responseCategory);
        assertEquals(categoryId, responseCategory.getCategoryId());
        assertEquals("Category1", responseCategory.getCategoryName());
        assertEquals("Description1", responseCategory.getDescription());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 5L;
        ResponseEntity<Object> response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 4L;
        
        CategoryDTO updatedCategory = new CategoryDTO(categoryId, "Updated Category", "Updated Description");
        when(categoryService.updateCategory(categoryId, updatedCategory)).thenReturn(updatedCategory);
        
        ResponseEntity<Object> response = categoryController.updateCategory(categoryId, updatedCategory);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CategoryDTO responseCategory = (CategoryDTO) response.getBody();
        assertNotNull(responseCategory);
        assertEquals(categoryId, responseCategory.getCategoryId());
        assertEquals("Updated Category", responseCategory.getCategoryName());
        assertEquals("Updated Description", responseCategory.getDescription());
    }
}
