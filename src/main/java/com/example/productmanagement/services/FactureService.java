package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Facture;
import com.example.productmanagement.Entities.Product;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Qualifier("defaultFactureService")
public interface FactureService {
    void saveFacture(Facture facture);
}
