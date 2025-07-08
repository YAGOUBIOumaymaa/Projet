package com.example.projet.controller;

import com.example.projet.entity.Client;
import com.example.projet.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//gestionne clients
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> creerClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.creerClient(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> listerClients() {
        return ResponseEntity.ok(clientService.listerClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return clientService.trouverClientParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

