# Gestion Notes & Absences – Architecture Microservices

Ce dépôt contient un projet complet de gestion des notes et des absences basé sur une architecture microservices avec backends Spring Boot et clients Desktop Swing.

## Contenu du projet

- **NotesService/** : service Spring Boot pour la gestion des notes des étudiants (port 8082).
- **AbsenceService/** : service Spring Boot pour la gestion des absences (port 8081).
- **ApiGateway/** : API Gateway (Spring Cloud Gateway) pour exposer les microservices via un point d'entrée unique (port 8079).
- **EurekaServer/** : serveur Eureka pour la découverte des services (port 8761).
- **NotesSwingClient/** : client Desktop Swing pour la gestion des notes.
- **AbsencesSwingClient/** : client Desktop Swing pour la gestion des absences.

## Objectif du projet

- Centraliser la gestion des **notes** et des **absences** des étudiants.
- Illustrer une architecture **microservices** avec :
  - découverte de services (Eureka),
  - routage via une API Gateway,
  - clients Desktop indépendants pour chaque domaine fonctionnel.

## Comment lancer le projet

### 1. Démarrer les bases de données PostgreSQL (Docker)

```bash
docker-compose up -d
```

Cela démarre deux conteneurs PostgreSQL :

- `postgres-notes` sur le port **5432** (base: gestion_notes_db)
- `postgres-absence` sur le port **5433** (base: gestion_absence_db)

### 2. Démarrer le serveur de découverte :

```bash
cd EurekaServer
mvn spring-boot:run
```

### 3. Démarrer les microservices backend :

```bash
cd AbsenceService
mvn spring-boot:run
```

```bash
cd NotesService
mvn spring-boot:run
```

### 4. Démarrer l'API Gateway :

```bash
cd ApiGateway
mvn spring-boot:run
```

### 5. Démarrer les clients Desktop Swing :

```bash
cd NotesSwingClient
mvn clean compile exec:java
```

```bash
cd AbsencesSwingClient
mvn clean compile exec:java
```

### Arrêter les bases de données

```bash
docker-compose down
```

## Technologies principales

- **Backends** : Java 21, Spring Boot 3.2, Spring Cloud, Eureka, API Gateway.
- **Clients** : Java Swing, Jersey Client, Jackson.
- **Base de données** : PostgreSQL 15 (via Docker).
- **Autres** : Maven, Docker Compose.
