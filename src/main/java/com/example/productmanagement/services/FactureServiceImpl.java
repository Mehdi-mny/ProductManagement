package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Facture;
import com.example.productmanagement.repositories.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
    private FactureRepository factureRepository;
    @Override
    public void saveFacture(Facture facture) {
        factureRepository.save(facture);

    }
}
