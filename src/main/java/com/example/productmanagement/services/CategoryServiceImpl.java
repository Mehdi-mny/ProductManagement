package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public Category updateCategory(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public void deleteCategory(String Ref) {
        categoryRepository.deleteById(Ref);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    @Override
    public Category findCategoryById(String Ref) {
        return categoryRepository.findById(Ref).get();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
