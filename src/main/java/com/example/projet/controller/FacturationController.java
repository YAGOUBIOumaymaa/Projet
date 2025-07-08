package com.example.projet.controller;

import com.example.projet.entity.Client;
import com.example.projet.entity.Facture;
import com.example.projet.repository.ClientRepository;
import com.example.projet.service.FactureService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FacturationController {


    private final FactureService factureService;

    public FacturationController( FactureService factureService) {

        this.factureService = factureService;
    }
    // Gestion factures

    @GetMapping("/factures")
    public List<Facture> getFactures() {
        return factureService.listerFactures();
    }

    @PostMapping("/factures")
    public ResponseEntity<?> creerFacture(@RequestBody Facture facture) {
        try {
            Facture f = factureService.creerFacture(facture);
            return ResponseEntity.ok(f);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //recherche par id client
    @GetMapping("/factures/recherche")
    public ResponseEntity<List<Facture>> rechercherFacturesParClient(@RequestParam Long clientId) {
        List<Facture> factures = factureService.listerFacturesParClientId(clientId);
        if (factures.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factures);
    }
    //recherche par date
    @GetMapping("/factures/recherche-par-date")
    public ResponseEntity<List<Facture>> rechercherFacturesParDate(@RequestParam String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<Facture> factures = factureService.listerFacturesParDate(parsedDate);
            if (factures.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(factures);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //recherche par id de la facture
    @GetMapping("/factures/{id}/export-json")
    public ResponseEntity<Facture> exportFacture(@PathVariable Long id) {
        return factureService.getFactureParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

