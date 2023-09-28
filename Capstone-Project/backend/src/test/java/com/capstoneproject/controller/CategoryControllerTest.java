package com.capstoneproject.controller;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryDTO> mockCategories = new ArrayList<>();
        when(categoryService.getCategories()).thenReturn(mockCategories);
        ResponseEntity<List<CategoryDTO>> response = categoryController.getAllCategories();
        verify(categoryService, times(1)).getCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategories, response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDTO mockCategory = new CategoryDTO();
        when(categoryService.getCategoryById(categoryId)).thenReturn(mockCategory);
        ResponseEntity<CategoryDTO> response = categoryController.getCategoryById(categoryId);
        verify(categoryService, times(1)).getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategory, response.getBody());
    }

    @Test
    public void testAddCategory() {
        CategoryDTO categoryDto = new CategoryDTO();
        ResponseEntity<String> response = categoryController.addCategory(categoryDto);
        verify(categoryService, times(1)).addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Added Category Successfully", response.getBody());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;
        ResponseEntity<String> response = categoryController.deleteCategory(categoryId);
        verify(categoryService, times(1)).deleteCategory(categoryId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Category Deleted", response.getHeaders().getFirst("Message"));
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDto = new CategoryDTO();
        ResponseEntity<String> response = categoryController.updateCategory(categoryId, updatedCategoryDto);
        verify(categoryService, times(1)).updateCategory(categoryId, updatedCategoryDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Category successfully", response.getBody());
    }
}
