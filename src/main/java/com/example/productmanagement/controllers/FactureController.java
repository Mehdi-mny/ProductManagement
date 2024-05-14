package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Facture;
import com.example.productmanagement.services.FactureService;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;



@Controller
@AllArgsConstructor
public class FactureController {
    @Qualifier("defaultFactureService")
    private FactureService factureService;
    @RequestMapping("/scanFacture")
    public String scanfacture(){
        return "OCR";
    }
    @RequestMapping("/saveFacture")
    public String savefacture(@RequestParam("imageFile") MultipartFile image) throws TesseractException, IOException {

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);

        File convFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(image.getBytes());
        fos.close();

        // Perform OCR on the converted File
        String result = tesseract.doOCR(convFile);
        System.out.println(result);
        convFile.delete();

        // Tokenize the OCR result
        StringTokenizer tokenizer = new StringTokenizer(result);

        // Search for "Total HT" and extract the total
        double total = 0.0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            System.out.println("Token: " + token); // Print the current token

            if (token.equalsIgnoreCase("TOTAL")) {
                // If "Total" is found, extract the total value
                if (tokenizer.hasMoreTokens()) {
                    String totalValueToken = tokenizer.nextToken().replaceAll(",", "."); // Replace comma with dot for parsing
                    try {
                        total = Double.parseDouble(totalValueToken); // Parse the total value
                        break; // Stop searching after finding the total value
                    } catch (NumberFormatException e) {
                        // Handle parsing error if total value is not a valid double
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("Total: " + total); // Print the extracted total value

        // Store the total
        Facture facture = new Facture();
        facture.setTotal(total);
        factureService.saveFacture(facture);
        return "OCR";
    }




}
