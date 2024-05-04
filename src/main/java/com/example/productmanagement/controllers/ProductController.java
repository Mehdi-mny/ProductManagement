package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.services.CategoryService;
import com.example.productmanagement.services.ProductService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
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
    public String saveProduct(@ModelAttribute("productVue") Product productController, RedirectAttributes redirectAttributes, @RequestParam("imageFile") MultipartFile image) throws IOException, WriterException {
        // Generate the QR code
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully!");
        // Save the product again to update the QR code
        ProductService.saveProduct(productController,image);
        return "redirect:createProduct";
    }

    @RequestMapping("/listProduct")
    public String listProduct(ModelMap modelMap){
        List<Product> productList = ProductService.getAllProducts();
        modelMap.addAttribute("productvue", productList);
        return "listProduct";
    }
    @RequestMapping("/upload-Products")
    public String uploadProducts(@ModelAttribute("fileForm") MultipartFile file,@ModelAttribute("imageFile") MultipartFile image){
        this.ProductService.saveProductsFromExcel(file);
        return "redirect:listProduct";
    }
    @GetMapping("/Excelsheet")
    public String ExcelSheet(){
        return "UploadFromExcel";}
}
