package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultCategoryService")
public interface CategoryService {
    void saveCategory(Category Category);
    Category updateCategory(Category Category);
    void deleteCategory(Long Id);
    void deleteAllCategories();
    Category findCategoryById(Long Id);
    List<Category> findAllCategories();
}
