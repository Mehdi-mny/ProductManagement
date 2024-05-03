package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@Qualifier("defaultProductService")
public interface ProductService {
    Product saveProduct(Product Product,MultipartFile file)throws IOException;
    Product updateProduct(Product Product);
    void deleteProduct(Long Id);
    void deleteAllProducts();
    Product getProductByRef(Long Id);
    List<Product> getAllProducts();
    public void saveProductsFromExcel(MultipartFile file);
}
