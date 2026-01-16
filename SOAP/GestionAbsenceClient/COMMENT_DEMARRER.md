# 🚀 Comment Démarrer l'Application

## Deux Fichiers avec la Méthode Main

Vous avez maintenant **DEUX façons** de démarrer l'application :

### Option 1 : Via ClientApp.java (Recommandé)
```bash
cd /home/mohamed/Documents/microservices/TP3Client
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"
```

### Option 2 : Via GestionAbsenceUI.java
```bash
cd /home/mohamed/Documents/microservices/TP3Client
mvn exec:java -Dexec.mainClass="org.example.client.GestionAbsenceUI"
```

## 📋 Étapes Complètes de Démarrage

### 1️⃣ Démarrer le Service Web (Serveur)

**Important** : Le service web doit être démarré AVANT le client !

Vérifiez que le service est accessible :
```bash
curl http://localhost:8090/absenceService?wsdl
```

Si vous voyez le WSDL XML, le service fonctionne ! ✅

### 2️⃣ Compiler le Projet Client

```bash
cd /home/mohamed/Documents/microservices/TP3Client
mvn clean compile
```

Vous devriez voir : `BUILD SUCCESS` ✅

### 3️⃣ Exécuter l'Application

**Méthode A - Via Maven (Recommandé)** :
```bash
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"
```

**Méthode B - Via Java directement** :
```bash
java -cp target/classes org.example.client.ClientApp
```

**Méthode C - Via le JAR** :
```bash
mvn clean package
java -jar target/TP3Client-1.0-SNAPSHOT.jar
```

**Méthode D - Depuis votre IDE (IntelliJ/Eclipse)** :
1. Ouvrez le projet dans votre IDE
2. Localisez `ClientApp.java` ou `GestionAbsenceUI.java`
3. Clic droit sur le fichier → "Run 'ClientApp.main()'" ou "Run 'GestionAbsenceUI.main()'"

## 🖥️ Depuis IntelliJ IDEA

### Configuration Run/Debug :
1. **Run** → **Edit Configurations...**
2. Cliquez sur **+** → **Application**
3. Remplissez :
   - **Name** : `Gestion Absence Client`
   - **Main class** : `org.example.client.ClientApp`
   - **Module** : `TP3Client`
   - **JRE** : Java 11 ou supérieur
4. Cliquez sur **Apply** puis **OK**
5. Cliquez sur le bouton ▶️ **Run**

### Ou plus simplement :
1. Ouvrez `ClientApp.java`
2. Vous verrez une icône ▶️ verte à côté de `public static void main`
3. Cliquez dessus → **Run 'ClientApp.main()'**

## 🎯 Vérifier que l'Application Démarre

Lorsque l'application démarre correctement, vous devriez voir :

✅ Une fenêtre avec le titre : **"Gestion de l'Absentéisme des Étudiants"**

✅ Deux onglets :
   - "Gestion des Étudiants"
   - "Liste Noire"

✅ Un formulaire avec les champs :
   - ID
   - CNE
   - Nom
   - Prénom
   - Niveau
   - Heures d'absence

✅ Des boutons :
   - Ajouter
   - Mettre à jour
   - Supprimer
   - Chercher Taux
   - Rafraîchir Liste
   - Vider

✅ Un tableau qui se remplit automatiquement avec les étudiants (si le service contient des données)

## ❌ Problèmes Courants

### Erreur : "Cannot connect to service"
**Cause** : Le service web n'est pas démarré

**Solution** :
```bash
# Vérifiez que le service fonctionne
curl http://localhost:8090/absenceService?wsdl

# Si ça ne fonctionne pas, démarrez votre service web
```

### Erreur : "ClassNotFoundException"
**Cause** : Le projet n'est pas compilé

**Solution** :
```bash
mvn clean compile
```

### Erreur : "Module not found" ou dépendances manquantes
**Cause** : Les dépendances Maven ne sont pas installées

**Solution** :
```bash
mvn clean install
```

### L'interface ne s'affiche pas
**Cause** : Problème d'affichage graphique (si vous êtes en SSH)

**Solution** :
```bash
# Configurez DISPLAY si nécessaire
export DISPLAY=:0

# Ou utilisez X11 forwarding
ssh -X user@host
```

### Le tableau est vide au démarrage
**Cause** : Pas d'étudiants dans la base de données OU service non accessible

**Solution** :
1. Vérifiez que le service est accessible
2. Ajoutez des étudiants via l'interface
3. Cliquez sur "Rafraîchir Liste"

## 📁 Structure des Fichiers avec Main

```
TP3Client/src/main/java/org/example/client/
├── ClientApp.java              ⭐ MAIN - Point d'entrée principal
├── GestionAbsenceUI.java       ⭐ MAIN - Alternative (ajouté)
├── AbsenceServiceClient.java   (Wrapper du service)
└── ... (autres classes générées par JAX-WS)
```

## 🔧 Commandes Rapides (Copier-Coller)

### Démarrage Rapide Complet
```bash
# 1. Aller dans le dossier du projet
cd /home/mohamed/Documents/microservices/TP3Client

# 2. Compiler
mvn clean compile

# 3. Exécuter
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"
```

### Démarrage avec Création du JAR
```bash
cd /home/mohamed/Documents/microservices/TP3Client
mvn clean package
java -jar target/TP3Client-1.0-SNAPSHOT.jar
```

### Démarrage en Mode Debug
```bash
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp" -X
```

## 📝 Notes Importantes

1. **Le service web DOIT être démarré en premier** sur http://localhost:8090/absenceService

2. **Java 11 ou supérieur** est requis

3. **L'application charge automatiquement** la liste des étudiants au démarrage

4. **Les deux fichiers Main** (`ClientApp` et `GestionAbsenceUI`) font exactement la même chose - utilisez celui que vous préférez

5. **Look and Feel Nimbus** : L'application utilise le thème Nimbus pour une interface moderne. Si non disponible, le thème par défaut sera utilisé.

## ✨ Prochaines Étapes

Une fois l'application démarrée :

1. **Testez l'ajout** d'un étudiant
2. **Vérifiez** que le tableau se met à jour
3. **Essayez la liste noire** avec différents seuils
4. **Consultez** le taux d'absence d'un étudiant

## 🆘 Besoin d'Aide ?

Si vous rencontrez des problèmes :

1. Vérifiez les logs dans la console
2. Assurez-vous que le service web fonctionne
3. Vérifiez que Maven est installé : `mvn --version`
4. Vérifiez que Java est installé : `java --version`

---

**Bon démarrage ! 🎉**

