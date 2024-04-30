package com.example.productmanagement.repositories;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
