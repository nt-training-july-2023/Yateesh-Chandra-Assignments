package com.capstoneproject.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.NoInputException;
import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category1", "Category Description1"));
        categories.add(new Category(2L, "Category2", "Category Description2"));
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDtos = categoryService.getAllCategories();
        assertEquals(2, categoryDtos.size());
        assertEquals("Category1", categoryDtos.get(0).getCategoryName());
        assertEquals("Category Description2", categoryDtos.get(1).getDescription());
    }

    @Test
    public void testaddCategory() {
        CategoryDTO categoryDto = new CategoryDTO(null, "New Category", "New Description");
        when(categoryRepository.getCategoryByName("New Category")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category(1L, "New Category", "New Description"));

        CategoryDTO addedCategory = categoryService.addCategory(categoryDto);
        assertNull(addedCategory.getCategoryId());
        assertEquals("New Category", addedCategory.getCategoryName());
        assertEquals("New Description", addedCategory.getDescription());
        verify(categoryRepository,times(1)).save(any(Category.class));
    }
    
    @Test
    public void testAddCategoryAlreadyExists() {
        CategoryDTO categoryDto = new CategoryDTO(null, "Existing Category", "Description");
        when(categoryRepository.getCategoryByName("Existing Category")).thenReturn(Optional.of(new Category()));
        assertThrows(AlreadyExistsException.class, () -> categoryService.addCategory(categoryDto));
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testAddCategoryNoInput() {
        CategoryDTO categoryDto = new CategoryDTO(null, "", "Description");
        assertThrows(NoInputException.class, () -> categoryService.addCategory(categoryDto));
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testDeleteCategory() {
        Long categoryIdToDelete = 1L;
        Category existingCategory = new Category(categoryIdToDelete, "Existing Category","Description");
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.of(existingCategory));
        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryIdToDelete));
        verify(categoryRepository, times(1)).deleteById(categoryIdToDelete);
    }

    @Test
    public void testDeleteCategoryNotFound() {
        Long categoryIdToDelete = 1L;
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> categoryService.deleteCategory(categoryIdToDelete));
        verify(categoryRepository, never()).deleteById(categoryIdToDelete);
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 2L;
        CategoryDTO updatedCategory = new CategoryDTO(categoryId, "Updated Category", "Updated Description");
        Category existingCategory = new Category(categoryId, "Existing Category", "Description");
        
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);
        
        CategoryDTO updatedCategoryDto = categoryService.updateCategory(categoryId, updatedCategory);
        assertEquals("Updated Category", updatedCategoryDto.getCategoryName());
        assertEquals("Updated Description", updatedCategoryDto.getDescription());
        verify(categoryRepository, times(1)).save(existingCategory);
    }

    @Test
    public void testUpdateCategoryNotFound() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDTO = new CategoryDTO(categoryId, "UpdatedCategory", "Updated Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> categoryService.updateCategory(categoryId, updatedCategoryDTO));
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testUpdateCategoryNoInput() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDTO = new CategoryDTO(categoryId, "", "Updated Description");
        Category existingCategory = new Category(categoryId, "ExistingCategory", "Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        assertThrows(NoInputException.class, () -> categoryService.updateCategory(categoryId, updatedCategoryDTO));
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "ExistingCategory", "Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        assertEquals("ExistingCategory", categoryDTO.getCategoryName());
        assertEquals("Description", categoryDTO.getDescription());
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoryService.getCategoryById(categoryId));
    }
}