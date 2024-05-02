package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultCategoryService")
public interface CategoryService {
    Category saveCategory(Category Category);
    Category updateCategory(Category Category);
    void deleteCategory(String Ref);
    void deleteAllCategories();
    Category findCategoryById(String Ref);
    List<Category> findAllCategories();
}
