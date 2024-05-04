package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.repositories.CategoryRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class ExcelUploadService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExcelUploadService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Votre méthode getProductsDataFromExcel et d'autres méthodes...

    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public static List<Product> getProductsDataFromExcel(InputStream inputStream) {
        List<Product> products = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Products");
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Product product = new Product();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            product.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setProvider(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setQuantity((int) cell.getNumericCellValue());
                            break;
                        case 4:
                            product.setUnit_cost((int) cell.getNumericCellValue());
                            break;
                        case 5:
                            product.setUnit_price((int) cell.getNumericCellValue());
                            break;

                        default:
                        break;
                    }
                    cellIndex++;
                }
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

}