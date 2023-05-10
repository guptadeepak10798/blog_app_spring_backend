package com.codewithdeepak.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdeepak.blog.payloads.ApiResponse;
import com.codewithdeepak.blog.payloads.CategoryDto;
import com.codewithdeepak.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/*
	 * POST -> CREATE CATEGORY
	 * 
	 * @Valid is used as validation
	 * 
	 */

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
	}

	/* PUT -> UPDATE CATEGORY */
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
//		return ResponseEntity.ok(updatedCategoryDto);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);
	}

	/* DELETE -> DELETE USER */

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid) {
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}

	/* GET -> GET Single USERS */

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") Integer cid) {
	CategoryDto singleCategoryDto =	this.categoryService.getCategory(cid);
//		return ResponseEntity.ok(singleCategoryDto);
	return new ResponseEntity<CategoryDto>(singleCategoryDto, HttpStatus.OK);
	}

	/* GET -> GET All USERS */

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAlCategories() {
		
		List<CategoryDto> allCategoryDto =	this.categoryService.getCategories();
//		return ResponseEntity.ok(this.categoryService.getCategories());
		return new ResponseEntity<List<CategoryDto>>(allCategoryDto, HttpStatus.OK);
	}

}
