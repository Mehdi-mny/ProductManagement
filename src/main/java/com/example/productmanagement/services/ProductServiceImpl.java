package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    @Override
    public Product saveProduct(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public Product updateProduct(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public void deleteProduct(String Ref) {
        productRepository.deleteById(Ref);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public Product getProductByRef(String Ref) {
        return productRepository.findById(Ref).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
