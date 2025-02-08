package com.example.shopapp.services;

import java.util.List;

import com.example.shopapp.dtos.CategoryDTO;
import com.example.shopapp.models.Category;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long categoryId, CategoryDTO category);

    void deleteCategory(Long id);
}
