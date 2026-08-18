package com.example.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.entity.Category;
import com.example.bookstore.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
//	1. /api/categories гэдэг GET controller үүсгэхдээ бүх findAllCategories гэдэг API үүсгээрэй.
	// List<Category> буцаадаг байна.

	@GetMapping
	public List<Category> findAll() {
		return categoryService.findAllCategories();
	}

//	2. /api/categories гэдэг createCategory гэдэг Category авдаг create гэдэг нэртэй POST 
	// API endpoint үүсгэнэ үү
	// Category буцаадаг байна.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category create(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}

//	3. /api/categories/{id} гэж pathvariable-аар id аваад түүнийг олоод
	// тухайн Category-г буцаадаг байна. findById гэдэг GET API endpoint байна.
	@GetMapping("/{id}")
	public Category findById(@PathVariable Long id) {
		return categoryService.findCategoryById(id);
	}

//	4. /api/categories/{id} гэдэг PathVariable id-г авдаг, RequestBody дээр нь Category төрөл
	// авдаг PUT API endpoint үүсгэнэ үү. Category буцаана.
	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

//	5. /api/categories/{id} гэдэг PathVariable id-г авдаг, утга буцаадаггүй DELETE API endpoint
//	бичнэ үү.
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
}
