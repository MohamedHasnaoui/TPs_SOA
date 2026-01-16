#!/bin/bash

# Script de démarrage de l'application Client Gestion Absence
# Utilisation : ./demarrer.sh

echo "=========================================="
echo "  Client Gestion Absentéisme Étudiants"
echo "=========================================="
echo ""

# Couleurs
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Vérifier que nous sommes dans le bon répertoire
if [ ! -f "pom.xml" ]; then
    echo -e "${RED}❌ Erreur: fichier pom.xml non trouvé${NC}"
    echo "Veuillez exécuter ce script depuis le répertoire du projet TP3Client"
    exit 1
fi

# Vérifier que Maven est installé
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}❌ Erreur: Maven n'est pas installé${NC}"
    echo "Installez Maven avec: sudo apt install maven"
    exit 1
fi

# Vérifier que Java est installé
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Erreur: Java n'est pas installé${NC}"
    echo "Installez Java avec: sudo apt install openjdk-11-jdk"
    exit 1
fi

echo -e "${YELLOW}📡 Vérification du service web...${NC}"
if curl -s http://localhost:8090/absenceService?wsdl > /dev/null 2>&1; then
    echo -e "${GREEN}✅ Service web accessible${NC}"
else
    echo -e "${RED}⚠️  ATTENTION: Le service web ne semble pas accessible${NC}"
    echo "URL attendue: http://localhost:8090/absenceService"
    echo ""
    read -p "Voulez-vous continuer quand même ? (o/N) " -n 1 -r
    echo ""
    if [[ ! $REPLY =~ ^[Oo]$ ]]; then
        echo "Démarrage annulé."
        exit 1
    fi
fi

echo ""
echo -e "${YELLOW}🔨 Compilation du projet...${NC}"
mvn clean compile

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Erreur lors de la compilation${NC}"
    exit 1
fi

echo ""
echo -e "${GREEN}✅ Compilation réussie${NC}"
echo ""
echo -e "${YELLOW}🚀 Démarrage de l'application...${NC}"
echo ""

# Démarrer l'application
mvn exec:java -Dexec.mainClass="org.example.client.ClientApp"

echo ""
echo -e "${YELLOW}Application terminée.${NC}"

