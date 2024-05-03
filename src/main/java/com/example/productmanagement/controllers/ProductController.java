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
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
    public String saveProduct(@ModelAttribute("productVue") Product productController,@RequestParam("imageFile") MultipartFile image) throws IOException {
        //Product saveProduct = ProductService.saveProduct(productController,image);
        // Generate the QR code
        //String qrCodeData = "Product ID: " + productController.getId() + "\n" +
          //      "Name: " + productController.getName() + "\n" +
            //    "Provider: " + productController.getProvider();
        //byte[] qrCodeBytes = generateQRCode(qrCodeData);

        // Set the QR code bytes in the product object
       // productController.setQrcode(qrCodeBytes);

        // Save the product again to update the QR code
        ProductService.saveProduct(productController,image);

        return "createProduct";
    }

    private byte[] generateQRCode(String data) throws IOException, WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 2, 2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
        return outputStream.toByteArray();
    }
    @RequestMapping("/listProduct")
    public String listProduct(ModelMap modelMap){
        List<Product> productList = ProductService.getAllProducts();
        modelMap.addAttribute("productvue", productList);
        return "listProduct";
    }
    @RequestMapping("/upload-Products")
    public ResponseEntity<?> uploadProducts(@ModelAttribute("fileForm") MultipartFile file){
        this.ProductService.saveProductsFromExcel(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
    }
    @GetMapping("/Excelsheet")
    public String ExcelSheet(){
        return "UploadFromExcel";}
}
