package com.capstoneproject.controller;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryDTO category1 = new CategoryDTO(1L, "Category Name",  "This is Description");
        CategoryDTO category2 = new CategoryDTO(1L, "Category Name",  "This is Description");
        categories.add(category1);
        categories.add(category2);
        assertEquals(2, categories.size());

        when(categoryService.getCategories()).thenReturn(categories);
        ResponseEntity<List<CategoryDTO>> response = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDTO category = new CategoryDTO(categoryId, "Category Name",  "This is Description");
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        ResponseEntity<CategoryDTO> response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testAddCategory() {
        CategoryDTO categoryDto = new CategoryDTO();

        ResponseEntity<Response> response = categoryController.addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.CREATED.value(), responseBody.getCode());
        assertEquals(SuccessMessages.CATEGORY_ADD_SUCCESS, responseBody.getMessage());

        verify(categoryService, times(1)).addCategory(categoryDto);
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;

        ResponseEntity<Response> response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.NO_CONTENT.value(), responseBody.getCode());
        assertEquals(SuccessMessages.CATEGORY_DELETE_SUCCESS, responseBody.getMessage());

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDto = new CategoryDTO();

        ResponseEntity<Response> response = categoryController.updateCategory(categoryId, updatedCategoryDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.OK.value(), responseBody.getCode());
        assertEquals(SuccessMessages.CATEGORY_UPDATED_SUCCESS, responseBody.getMessage());

        verify(categoryService, times(1)).updateCategory(categoryId, updatedCategoryDto);
    }
}
