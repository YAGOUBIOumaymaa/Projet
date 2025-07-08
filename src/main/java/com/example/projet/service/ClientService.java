package com.example.projet.service;

import com.example.projet.entity.Client;
import com.example.projet.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client creerClient(Client client) {
        if (client.getNom() == null || client.getEmail() == null || client.getSiret() == null) {
            throw new IllegalArgumentException("Tous les champs du client sont obligatoires");
        }
        return clientRepository.save(client);
    }
    //liste des clients

    public List<Client> listerClients() {
        return clientRepository.findAll();
    }
    //
    public Optional<Client> trouverClientParId(Long id) {
        return clientRepository.findById(id);
    }
}
