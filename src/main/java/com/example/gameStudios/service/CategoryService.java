package com.example.gameStudios.service;

import com.example.gameStudios.entity.Category;
import com.example.gameStudios.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Optional<Category> find(UUID id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    public void updateCategory(UUID categoryId, String categoryName) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            category.setName(categoryName);
            categoryRepository.save(category);
        }
    }

    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public UUID getCategoryIdByName(String categoryName) {
        return categoryRepository.findByName(categoryName).getId();
    }
}
