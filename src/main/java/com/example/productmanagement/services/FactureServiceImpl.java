package com.example.productmanagement.services;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Facture;
import com.example.productmanagement.repositories.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
    private FactureRepository factureRepository;

    @Override
    public void deleteFacture(Long factureID) {
        factureRepository.deleteById(factureID);
    }

    @Override
    public void saveFacture(Facture facture) {
        factureRepository.save(facture);

    }

    @Override
    public List<Facture> findAllCategories() {
        return factureRepository.findAll();
    }
}
