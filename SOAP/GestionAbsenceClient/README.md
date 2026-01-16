# Client JAX-WS pour la Gestion de l'Absentéisme des Étudiants

## Description

Ce projet est un client Java Swing pour consommer le service web JAX-WS de gestion d'absentéisme des étudiants. Il offre une interface graphique moderne et intuitive permettant d'effectuer toutes les opérations CRUD (Create, Read, Update, Delete) sur les étudiants, ainsi que de générer une "liste noire" basée sur le taux d'absentéisme.

## Architecture

Le projet est structuré en trois classes principales :

### 1. AbsenceServiceClient.java
Classe wrapper qui encapsule la logique d'interaction avec le service web JAX-WS. Elle fournit des méthodes simplifiées pour :
- **ajouterEtudiant()** : Ajoute un nouvel étudiant
- **mettreAJourEtudiant()** : Met à jour un étudiant existant
- **supprimerEtudiant()** : Supprime un étudiant par son ID
- **obtenirTauxAbsence()** : Retourne le taux d'absence d'un étudiant (méthode centrale pour l'intégration avec d'autres services)
- **genererListeNoire()** : Génère la liste des étudiants dépassant un seuil d'absence
- **creerEtudiant()** : Méthode utilitaire pour créer un objet Etudiant

### 2. GestionAbsenceUI.java
Interface graphique principale utilisant Swing avec les caractéristiques suivantes :

#### Onglet "Gestion des Étudiants"
- **Formulaire** : Saisie des informations (ID, CNE, Nom, Prénom, Niveau, Heures d'absence)
- **Boutons d'action** :
  - **Ajouter** : Crée un nouvel étudiant
  - **Mettre à jour** : Modifie un étudiant existant
  - **Supprimer** : Supprime un étudiant (avec confirmation)
  - **Chercher Taux** : Affiche le taux d'absence d'un étudiant
  - **Vider** : Réinitialise le formulaire
- **Tableau** : Affiche la liste des étudiants (sélection pour remplir le formulaire)

#### Onglet "Liste Noire"
- **Configuration** : JSpinner pour définir le seuil d'absence (0-100%)
- **Bouton Générer** : Crée la liste des étudiants dépassant le seuil
- **Tableau des résultats** : Affiche les étudiants triés par heures d'absence puis par nom

#### Caractéristiques techniques
- **Look and Feel Nimbus** : Interface moderne et esthétique
- **SwingWorker** : Appels asynchrones au service web pour ne pas bloquer l'interface
- **Validation des données** : Contrôles de saisie avec messages d'erreur explicites
- **Feedback utilisateur** : JOptionPane pour les messages de succès/erreur
- **Gestion des événements** : ActionListener pour tous les boutons
- **Sélection dans le tableau** : Remplissage automatique du formulaire lors de la sélection

### 3. ClientApp.java
Point d'entrée de l'application qui lance l'interface graphique sur l'Event Dispatch Thread (EDT) avec gestion des erreurs de connexion au service.

## Modèle de Données

La classe **Etudiant** (générée par JAX-WS) contient :
- **id** (long) : Identifiant unique
- **cne** (String) : Code National de l'Étudiant
- **nom** (String) : Nom de famille
- **prenom** (String) : Prénom
- **niveau** (String) : Niveau d'études (ex: L1, L2, L3, M1, M2)
- **nombreHeuresAbsence** (int) : Nombre d'heures d'absence

## Service Web

Le client consomme un service JAX-WS exposant les opérations suivantes :

| Opération | Paramètre | Retour | Description |
|-----------|-----------|--------|-------------|
| add | Etudiant | void | Ajoute un étudiant |
| update | Etudiant | void | Met à jour un étudiant |
| delete | long (id) | void | Supprime un étudiant |
| read | long (id) | double | Retourne le taux d'absence en % |
| blackListCreate | int (seuil) | List<Etudiant> | Retourne les étudiants dépassant le seuil |

**URL du WSDL** : http://localhost:8090/absenceService?wsdl

## Prérequis

- **Java 11 ou supérieur**
- **Maven 3.6+**
- **Service web d'absentéisme** démarré sur http://localhost:8090/absenceService

## Compilation

```bash
mvn clean compile
```

## Génération du client JAX-WS (si nécessaire)

Pour régénérer les stubs du client à partir du WSDL :

```bash
mvn clean jaxws:wsimport compile
```

## Exécution

### Via Maven
```bash
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"
```

### Via JAR
```bash
mvn clean package
java -jar target/TP3Client-1.0-SNAPSHOT.jar
```

### Directement avec Java
```bash
java -cp target/classes org.example.client.ClientApp
```

## Utilisation

### Ajouter un étudiant
1. Remplir tous les champs du formulaire
2. Cliquer sur "Ajouter"
3. Un message de confirmation s'affiche

### Mettre à jour un étudiant
1. Remplir tous les champs (l'ID identifie l'étudiant à modifier)
2. Cliquer sur "Mettre à jour"
3. Un message de confirmation s'affiche

### Supprimer un étudiant
1. Saisir l'ID de l'étudiant
2. Cliquer sur "Supprimer"
3. Confirmer la suppression dans la boîte de dialogue

### Consulter le taux d'absence
1. Saisir l'ID de l'étudiant
2. Cliquer sur "Chercher Taux"
3. Le taux s'affiche dans une boîte de dialogue

### Générer la liste noire
1. Aller dans l'onglet "Liste Noire"
2. Définir le seuil d'absence (ex: 50 pour 50%)
3. Cliquer sur "Générer la Liste Noire"
4. Les résultats s'affichent dans le tableau

### Sélectionner un étudiant dans le tableau
- Cliquer sur une ligne du tableau remplit automatiquement le formulaire
- Permet de modifier ou supprimer facilement un étudiant existant

## Bonnes Pratiques Implémentées

### 1. Architecture en couches
- **Couche de présentation** : GestionAbsenceUI (interface utilisateur)
- **Couche métier** : AbsenceServiceClient (logique d'appel au service)
- **Couche de service** : Service JAX-WS distant

### 2. Asynchronisme
- Utilisation de **SwingWorker** pour tous les appels au service web
- Évite le blocage de l'interface utilisateur
- Mise à jour des composants Swing sur l'EDT (Event Dispatch Thread)

### 3. Validation des données
- Vérification de la présence de toutes les valeurs
- Validation des types (nombres entiers pour ID et heures)
- Contrôle de cohérence (heures d'absence positives)

### 4. Gestion des erreurs
- Try-catch autour des appels au service
- Messages d'erreur explicites à l'utilisateur
- Gestion de l'indisponibilité du service au démarrage

### 5. Expérience utilisateur
- Look and Feel Nimbus moderne
- Messages de confirmation pour les opérations critiques
- Sélection dans les tableaux pour faciliter la modification
- Bouton "Vider" pour réinitialiser le formulaire
- Mise au point automatique sur le champ ID après vidage

## Intégration avec d'autres services (Point 3 du TP)

La méthode **obtenirTauxAbsence(long id)** de la classe **AbsenceServiceClient** est conçue pour être facilement réutilisée par d'autres services, notamment le service de gestion des notes.

### Exemple d'utilisation
```java
AbsenceServiceClient absenceClient = new AbsenceServiceClient();
double tauxAbsence = absenceClient.obtenirTauxAbsence(studentId);

// Le service de gestion des notes peut utiliser ce taux
// pour pénaliser la note finale
double noteFinale = noteInitiale * (1 - tauxAbsence / 100);
```

Cette approche permet une intégration claire et découplée entre les différents services du système.

## Dépannage

### Erreur de connexion au service
**Symptôme** : Message d'erreur au démarrage ou lors des opérations

**Solution** :
1. Vérifier que le service web est démarré
2. Vérifier l'URL dans **AbsenceServiceImplService.java** (ligne 33)
3. Tester l'accès au WSDL : http://localhost:8090/absenceService?wsdl

### Erreurs de compilation
**Symptôme** : Classes Etudiant, AbsenceService non trouvées

**Solution** :
1. Régénérer les stubs JAX-WS : `mvn jaxws:wsimport`
2. Recompiler : `mvn clean compile`

### Interface graphique ne s'affiche pas
**Symptôme** : L'application se lance mais aucune fenêtre n'apparaît

**Solution** :
1. Vérifier les logs de la console
2. S'assurer que DISPLAY est configuré (si sous Linux avec SSH)
3. Essayer de forcer le Look and Feel par défaut en commentant appliquerLookAndFeel()

## Structure du Projet

```
TP3Client/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── org/
        │       └── example/
        │           └── client/
        │               ├── AbsenceService.java (généré)
        │               ├── AbsenceServiceImplService.java (généré)
        │               ├── Etudiant.java (généré)
        │               ├── Add.java (généré)
        │               ├── AddResponse.java (généré)
        │               ├── ... (autres classes générées)
        │               ├── AbsenceServiceClient.java (développé)
        │               ├── GestionAbsenceUI.java (développé)
        │               └── ClientApp.java (développé)
        └── resources/
```

## Auteur

Projet développé dans le cadre du TP3 - Services Web JAX-WS

## Licence

Ce projet est à usage éducatif.

