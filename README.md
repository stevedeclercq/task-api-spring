# Task API (Spring Boot)

API REST simple de gestion de tâches : créer des tâches avec une description et suivre leur état (fait / non fait).

## Fonctionnalités
- CRUD complet : Create / Read / Update / Delete
- Création d’une tâche
- Lecture de toutes les tâches ou d’une tâche par id
- Mise à jour complète d’une tâche (PUT)
- Suppression d’une tâche
- Champs : `description` (String), `done` (boolean)

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

Body (JSON) :
{
"description": "Ma tâche",
"done": false
}

---

### Récupérer toutes les tâches
GET /api/tasks

---

### Récupérer une tâche par id
GET /api/tasks/{id}

---

### Mettre à jour une tâche
PUT /api/tasks/{id}

Body (JSON) :
{
"description": "Tâche modifiée",
"done": true
}

---

### Supprimer une tâche
DELETE /api/tasks/{id}

