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
    public String savefacture( @RequestParam("imageFile") MultipartFile image) throws TesseractException, IOException {

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
        convFile.delete();
        Facture facture=new Facture();
        facture.setTotal(Double.parseDouble(result));
        factureService.saveFacture(facture);
        return "OCR";
    }

}
