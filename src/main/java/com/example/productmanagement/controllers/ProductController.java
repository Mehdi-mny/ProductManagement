package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.services.CategoryService;
import com.example.productmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    @Qualifier("defaultCategoryService")
    private CategoryService CategoryService;

    @Qualifier("defaultProductService")
    private ProductService ProductService;

    @RequestMapping("/createProduct")
    public String createProduct(ModelMap model) {
        List<Category> categoriesList = CategoryService.findAllCategories();
        model.addAttribute("categoriesList", categoriesList);
        return "createProduct";
    }
    @RequestMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("productVue") Product productController){
        Product saveProduct = ProductService.saveProduct(productController);
        return "createProduct";
    }
    @RequestMapping("/listProduct")
    public String listProduct(ModelMap modelMap){
        List<Product> productList = ProductService.getAllProducts();
        modelMap.addAttribute("productvue", productList);
        return "listProduct";
    }
}
