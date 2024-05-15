package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.repositories.ProductRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    // Save avec image
    @Override
    public void saveProduct(Product Product, MultipartFile file) throws IOException, WriterException {
        Product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        // Set the QR code bytes in the product object
        Product.setQrcode(Base64.getEncoder().encodeToString(this.qrcode(Product)));
        productRepository.save(Product);
    }
    // save sans image
    public void saveProduct(Product Product) throws IOException, WriterException {
        // Set the QR code bytes in the product object
        Product.setQrcode(Base64.getEncoder().encodeToString(this.qrcode(Product)));
        productRepository.save(Product);
    }
    public byte[] qrcode(Product Product) throws IOException, WriterException {
        String qrCodeData = "Product Name: \n" +
                Product.getName() + "\n" +
                "Provider: " + Product.getProvider();

        // Set the QR code bytes in the product object

        return generateQRCode(qrCodeData);
    }
    private byte[] generateQRCode(String data) throws IOException, WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 2, 2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "jpeg", outputStream);
        return outputStream.toByteArray();
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
                List<Product> products = ExcelUploadService.getProductsDataFromExcel(file.getInputStream());
               for(Product product:products){
                this.saveProduct(product);
               }
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
