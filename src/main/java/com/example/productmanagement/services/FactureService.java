package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Facture;
import com.example.productmanagement.Entities.Product;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Qualifier("defaultFactureService")
public interface FactureService {
    void deleteFacture(Long factureID);

    void saveFacture(Facture facture);

    List<Facture> findAllCategories();
}
