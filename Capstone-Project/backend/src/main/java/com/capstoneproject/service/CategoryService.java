package com.capstoneproject.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		
		return categoryRepository.findAll();
		
	}
	
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
	public Category updateCategory(Long categoryId, Category updatedCategory) {
	
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category Not Found"));
		existingCategory.setCategoryName(updatedCategory.getCategoryName());
		existingCategory.setDescription(updatedCategory.getDescription());
		
		return categoryRepository.save(existingCategory);
	}

	public Category getCategoryById(Long categoryId) {
		
		try {
		Category existingCategory = categoryRepository.findById(categoryId).get();		
		return existingCategory;
		}
		catch(NoSuchElementException e) {
			throw new EntityNotFoundException("Element is not found");
		}
	}
	
}
