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
    public void deleteCategory(Long Id) {
        categoryRepository.deleteById(Id);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    @Override
    public Category findCategoryById(Long Id) {
        return categoryRepository.findById(Id).get();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
