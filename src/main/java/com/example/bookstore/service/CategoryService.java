package com.example.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
//	findAllCategories гэдэг public List<Category> буцаадаг method бичнэ үү.
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}
	
	
//	findCategoryById гэдэг Category буцаадаг id Long parameter авдаг method бичнэ үү.
//	orElseThrow ашиглаарай.
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}
	
//	createCategory гэдэг Category Entity параметр авдаг Category төрөл буцаадаг method бичнэ үү
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
//	updateCategory гэдэг Long id, Category newCategory parameter авдаг method бичнэ үү
//	Category буцаана. Хамгийн гол нь эхлээд тухайн категори байгаа эсэхийг шалгаад 
//	байхгүй бол orElseThrow хийнэ.
	public Category updateCategory(Long id, Category newCategory) {
		Category foundCategory = categoryRepository.findById(id).orElseThrow();
		
		foundCategory.setName(newCategory.getName());
		
		return categoryRepository.save(foundCategory);
	}
	
//	deleteCategory гэдэг Long id авдаг утга буцаадаггүй функц бичээрэй. Хэрвээ тухайн id олдохгүй бол
//	orElseThrow хийнэ.
	public void deleteCategory(Long id) {
		Category foundCategory = categoryRepository.findById(id).orElseThrow();
		categoryRepository.delete(foundCategory);
	}
	
}
