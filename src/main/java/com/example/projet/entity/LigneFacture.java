package com.example.projet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LigneFacture {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        private int quantite;
        private double prixUnitaireHT;
        private double tauxTVA;
        @ManyToOne
        @JoinColumn(name = "facture_id")
        private Facture facture;

        // Getters et setters

    }

