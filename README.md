#  Mini projet de gestion des client et des facteurs

##  Objectif du projet

Développer un mini_projet en Java/Spring Boot pour gérer :
- Les clients
- Les factures (avec lignes de facturation, TVA, total calculé)
- L'export au format JSON


## ⚙️ Technologies utilisées

- Java 17
- Spring Boot 3.5
- Maven
- H2 (base de données en mémoire)
- Spring Data JPA
- Spring Web
- Postman (pour les tests des endpoints REST)

##  Fonctionnalités

### Gestion des Clients :
- Liste des clients : `GET http://localhost:8080/clients`
- Création de client : `POST http://localhost:8080/clients`

### Gestion des Factures :
- Liste : `GET http://localhost:8080/api/factures`
- Création : `POST http://localhost:8080/api/factures`
- Détail : `GET http://localhost:8080/api/factures/{id}`
- Recherche par id client  : `GET http://localhost:8080/api/factures/recherche?clientId=1`
- Recherche par date  : `GET http://localhost:8080/api/factures/recherche?date=2025-07-08`

###  Export JSON :
- Export d'une facture complète via `GET http://localhost:8080/api/factures/{id}`

---

## ✅ Règles métier
- Une facture doit contenir au moins **une ligne**.
- Aucun champ obligatoire ne doit être vide.
- Taux de TVA autorisés : **0%, 5.5%, 10%, 20%**.
- Les totaux sont calculés automatiquement.


## 🧪 Tests avec Postman

Voici des exemples de requêtes testées :
### ➕ Créer un client

```json
POST http://localhost:8080/clients
{
  "nom": "Entreprise ABC",
  "email": "contact@entrepriseabc.com",
  "siret": "1234567890011"
}


###➕ Créer une facture
POST http://localhost:8080/api/factures
{
  "client": { "id": 1 },
  "date": "2025-07-08",
  "lignes": [
    {
      "description": "Développement",
      "quantite": 5,
      "prixUnitaireHT": 100,
      "tauxTVA": 0.2
    }
  ]
}
