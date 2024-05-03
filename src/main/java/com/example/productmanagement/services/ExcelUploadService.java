package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public static List<Product> getCustomersDataFromExcel(InputStream inputStream){
        List<Product> products = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Products");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Product product = new Product();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> product.setId(Long.valueOf((int) cell.getNumericCellValue()));
                        case 1 -> product.setName(cell.getStringCellValue());
                        case 2 -> product.setProvider(cell.getStringCellValue());
                        case 3 -> product.setQuantity((int) cell.getNumericCellValue());
                        case 4 -> product.setUnit_cost((int) cell.getNumericCellValue());
                        case 5 -> product.setUnit_price((int) cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                products.add(product);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return products;
    }

}