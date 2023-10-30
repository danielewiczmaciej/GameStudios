package com.example.gameStudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    public Category createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public UUID getCategoryIdByName(String categoryName) {
        return categoryRepository.findByName(categoryName).getId();
    }
}
