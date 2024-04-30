package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @RequestMapping("/createProduct")
    public String createProduct(){
        return "createProduct";
    }

    @RequestMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("productVue") Product productController){
        Product saveProduct = productService.saveProduct(productController);
        return "createProduct";
    }
}
