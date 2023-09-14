package com.codewithdeepak.blog.services;

import java.util.List;

import com.codewithdeepak.blog.payloads.CategoryDto;

public interface CategoryService {

	// CREATE
	CategoryDto createCategory(CategoryDto caterogyDto);

	// UPDATE
	CategoryDto updateCategory(CategoryDto caterogyDto, Integer categoryId);

	// DELETE
	void deleteCategory(Integer categoryId);

	// GET
	CategoryDto getCategory(Integer categoryId);

	// GET ALL
	List<CategoryDto> getCategories();
}
