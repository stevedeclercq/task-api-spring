# Task API (Spring Boot)

API REST de gestion de tâches développée avec Spring Boot.
L’application permet de créer, modifier, consulter et supprimer des tâches, avec gestion de relations entre entités : catégories, priorités, statuts et tags.
Ce projet met en pratique une architecture backend en couches avec Controller, Service, Repository, JPA/Hibernate et une base H2 en mémoire.

## Fonctionnalités

- CRUD complet sur les tâches : Create / Read / Update / Delete
- CRUD sur les entités liées :
    - Categories
    - Priorities
    - Statuses
    - Tags
- Gestion des relations JPA :
    - Task → Category : ManyToOne
    - Task → Priority : ManyToOne
    - Task → Status : ManyToOne
    - Task → Tags : ManyToMany
- Création d’une tâche avec ses relations via JSON
- Mise à jour complète d’une tâche avec PUT
- Suppression d’une tâche

## Tech stack
- Java
- Spring Boot (REST)
- Spring Data JPA
- Base de données : H2 (en mémoire, pour le développement)

## Objectif du projet
Ce projet a été réalisé dans le cadre de mon apprentissage de Spring Boot et du développement d’API REST,
suite à une formation et à un accompagnement/coaching personnel sur la technologie.

Il s’inscrit dans la continuité de mon apprentissage du langage Java, avec pour objectif de pouvoir construire
des applications web complètes à terme.

## Lancer le projet

### Prérequis
- Java 17 (ou version compatible avec ton projet)
- Maven

### Démarrage

mvn spring-boot:run

L’application démarre par défaut sur :

http://localhost:8080

---

## Endpoints

### Créer une tâche
POST /api/tasks
```json
{
  "title": "Ma première tâche",
  "description": "Description de test",
  "done": false,
  "category": { "id": 1 },
  "priority": { "id": 1 },
  "status": { "id": 1 },
  "tags": [
    { "id": 1 }
  ]
}
```

---

### Récupérer toutes les tâches
GET /api/tasks

---

### Récupérer une tâche par id
GET /api/tasks/{id}

---

### Mettre à jour une tâche
PUT /api/tasks/{id}
```json
{
"description": "Tâche modifiée",
"done": true
}
```
---

### Supprimer une tâche
DELETE /api/tasks/{id}

### Endpoints des entités liées

Les entités suivantes disposent également d’endpoints CRUD :

- `/api/categories`
- `/api/priorities`
- `/api/statuses`
- `/api/tags`