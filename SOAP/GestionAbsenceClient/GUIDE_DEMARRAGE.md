# Guide de Démarrage Rapide

## ✅ Problème Résolu : Le tableau n'affichait pas les étudiants

### Ce qui a été ajouté :

1. **Méthode `obtenirTousLesEtudiants()`** dans `AbsenceServiceClient.java`
2. **Méthode `chargerListeEtudiants()`** dans `GestionAbsenceUI.java`
3. **Bouton "Rafraîchir Liste"** pour recharger manuellement les données
4. **Chargement automatique** au démarrage et après chaque opération CRUD

## Comment utiliser l'application maintenant

### 1. Démarrer le service web (serveur)
```bash
# Assurez-vous que votre service web est démarré sur http://localhost:8090/absenceService
```

### 2. Compiler le client
```bash
cd /home/mohamed/Documents/microservices/TP3Client
mvn clean compile
```

### 3. Exécuter le client
```bash
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"
```

ou

```bash
java -cp target/classes org.example.client.ClientApp
```

### 4. Utiliser l'interface

#### Au démarrage :
- ✅ **Le tableau se remplit automatiquement** avec tous les étudiants du service

#### Onglet "Gestion des Étudiants" :

**Ajouter un étudiant :**
1. Remplir tous les champs (ID, CNE, Nom, Prénom, Niveau, Heures d'absence)
2. Cliquer sur "Ajouter"
3. ✅ **Le tableau se met à jour automatiquement**

**Modifier un étudiant :**
1. **Méthode 1** : Cliquer sur une ligne dans le tableau → le formulaire se remplit
2. **Méthode 2** : Saisir manuellement les informations
3. Modifier les champs souhaités
4. Cliquer sur "Mettre à jour"
5. ✅ **Le tableau se met à jour automatiquement**

**Supprimer un étudiant :**
1. **Méthode 1** : Cliquer sur une ligne dans le tableau pour sélectionner l'étudiant
2. **Méthode 2** : Saisir l'ID dans le champ
3. Cliquer sur "Supprimer"
4. Confirmer la suppression
5. ✅ **Le tableau se met à jour automatiquement**

**Consulter le taux d'absence :**
1. Saisir l'ID de l'étudiant
2. Cliquer sur "Chercher Taux"
3. Le taux s'affiche dans une boîte de dialogue

**Rafraîchir manuellement la liste :**
1. Cliquer sur "Rafraîchir Liste"
2. ✅ **Le tableau se recharge avec les données actuelles**

**Vider le formulaire :**
1. Cliquer sur "Vider"
2. Tous les champs sont réinitialisés

#### Onglet "Liste Noire" :

**Générer la liste noire :**
1. Définir le seuil d'absence (ex: 50 pour 50%)
2. Cliquer sur "Générer la Liste Noire"
3. Les étudiants dépassant le seuil s'affichent, triés par heures d'absence

## Fonctionnalités clés

### ✨ Tableau interactif
- **Cliquer sur une ligne** remplit automatiquement le formulaire
- **Facilite la modification** et la suppression
- **Se met à jour automatiquement** après chaque opération

### ⚡ Asynchrone
- **Appels non bloquants** au service web avec SwingWorker
- **Interface réactive** qui ne se fige jamais

### 🎨 Interface moderne
- **Look and Feel Nimbus** pour une apparence professionnelle
- **Organisation en onglets** pour séparer les fonctionnalités
- **Messages clairs** de succès et d'erreur

### 🛡️ Robuste
- **Validation des données** avant l'envoi
- **Gestion des erreurs** avec messages explicites
- **Confirmation** avant les opérations critiques (suppression)

## Structure du code

```
TP3Client/src/main/java/org/example/client/
├── AbsenceService.java           (généré par JAX-WS)
├── AbsenceServiceImplService.java (gén��ré par JAX-WS)
├── Etudiant.java                  (généré par JAX-WS)
├── AbsenceServiceClient.java      ⭐ Wrapper du service
├── GestionAbsenceUI.java          ⭐ Interface graphique Swing
└── ClientApp.java                 ⭐ Point d'entrée
```

## Méthodes principales

### AbsenceServiceClient
- `ajouterEtudiant(Etudiant)` - Ajoute un étudiant
- `mettreAJourEtudiant(Etudiant)` - Met à jour un étudiant
- `supprimerEtudiant(long)` - Supprime un étudiant
- `obtenirTauxAbsence(long)` - ⭐ Obtient le taux T (pour intégration avec d'autres services)
- `genererListeNoire(int)` - Génère la liste noire
- `obtenirTousLesEtudiants()` - ⭐ **NOUVEAU** Récupère tous les étudiants

### GestionAbsenceUI
- `chargerListeEtudiants()` - ⭐ **NOUVEAU** Charge et affiche tous les étudiants
- `ajouterEtudiant()` - Action d'ajout
- `mettreAJourEtudiant()` - Action de mise à jour
- `supprimerEtudiant()` - Action de suppression
- `chercherTauxAbsence()` - Action de recherche du taux
- `genererListeNoire()` - Action de génération de la liste noire

## Dépannage

### Le tableau reste vide au démarrage

**Causes possibles :**
1. Le service web n'est pas démarré
2. Le service web n'est pas accessible sur http://localhost:8090/absenceService
3. Il n'y a aucun étudiant dans la base de données

**Solutions :**
1. Vérifier que le service est démarré : `curl http://localhost:8090/absenceService?wsdl`
2. Ajouter des étudiants via l'interface
3. Cliquer sur "Rafraîchir Liste" après avoir démarré le service

### Erreur lors des opérations

**Vérifier :**
1. Le service web est accessible
2. Les données saisies sont valides (ID numérique, heures positives)
3. L'étudiant existe (pour modification/suppression)

## Notes importantes

### Intégration avec le service de gestion des notes

La méthode `obtenirTauxAbsence(long id)` est conçue pour être facilement utilisée par le service de gestion des notes :

```java
// Dans le service de gestion des notes
AbsenceServiceClient absenceClient = new AbsenceServiceClient();
double tauxAbsence = absenceClient.obtenirTauxAbsence(studentId);
double noteFinale = noteInitiale * (1 - tauxAbsence / 100);
```

### Méthode technique utilisée

Comme le service ne fournit pas de méthode `getAllStudents()`, nous utilisons `blackListCreate(0)` qui retourne tous les étudiants ayant un taux >= 0%.

## Prochaines améliorations possibles

1. **Pagination** : Pour gérer un grand nombre d'étudiants
2. **Recherche** : Filtre par nom, CNE, niveau
3. **Export** : Exporter la liste en CSV/PDF
4. **Statistiques** : Graphiques de l'absentéisme
5. **Tri** : Cliquer sur les colonnes pour trier

---

**Bon travail avec votre application ! 🎉**

