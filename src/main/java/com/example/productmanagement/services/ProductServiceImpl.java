package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    @Override
    public Product saveProduct(Product Product, MultipartFile file) throws IOException {
        Product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return productRepository.save(Product);
    }
    @Override
    public Product updateProduct(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public void deleteProduct(Long Id) {
        productRepository.deleteById(Id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public Product getProductByRef(Long Id) {
        return productRepository.findById(Id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().toList();
    }
    @Override
    public void saveProductsFromExcel(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Product> products = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
                this.productRepository.saveAll(products);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
