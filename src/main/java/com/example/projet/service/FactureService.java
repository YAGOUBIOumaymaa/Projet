package com.example.projet.service;

import com.example.projet.entity.Facture;
import com.example.projet.repository.FactureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FactureService {

    private final FactureRepository factureRepository;
    private static final List<Double> TAUX_TVA_AUTORISES = Arrays.asList(0.0, 0.055, 0.10, 0.20);


    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Transactional
    public Facture creerFacture(Facture facture) {
        if (facture.getLignes() == null || facture.getLignes().isEmpty()) {
            throw new IllegalArgumentException("Une facture doit avoir au moins une ligne");
        }

        double totalHT = 0.0;
        double totalTVA = 0.0;

        for (var ligne : facture.getLignes()) {
            if (!TAUX_TVA_AUTORISES.contains(ligne.getTauxTVA())) {
                throw new IllegalArgumentException("Le taux de TVA " + ligne.getTauxTVA() + " n'est pas valide. Taux autorisés : " + TAUX_TVA_AUTORISES);
            }
            double ligneHT = ligne.getPrixUnitaireHT() * ligne.getQuantite();
            double ligneTVA = ligneHT * ligne.getTauxTVA();
            totalHT += ligneHT;
            totalTVA += ligneTVA;
            ligne.setFacture(facture);
        }

        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT + totalTVA);

        return factureRepository.save(facture);
    }

    public Optional<Facture> getFactureParId(Long id) {
        return factureRepository.findById(id);
    }

    public List<Facture> listerFactures() {
        return factureRepository.findAll();
    }
    public List<Facture> listerFacturesParClientId(Long clientId) {
        return factureRepository.findByClientId(clientId);
    }
    public List<Facture> listerFacturesParDate(LocalDate date) {
        return factureRepository.findByDate(date);
    }

}



