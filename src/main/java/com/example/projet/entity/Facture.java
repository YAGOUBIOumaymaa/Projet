package com.example.projet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Facture {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate date;

        @ManyToOne
        private Client client;

        @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
        private List<LigneFacture> lignes;

        private double totalHT;
        private double totalTVA;
        private double totalTTC;

        // Getters et setters
    }


