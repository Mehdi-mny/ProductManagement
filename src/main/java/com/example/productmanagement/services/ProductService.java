package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    Product saveProduct(Product Product);
    Product updateProduct(Product Product);
    void deleteProduct(String Ref);
    void deleteAllProducts();
    Product getProductByRef(String Ref);
    List<Product> getAllProducts();
}
