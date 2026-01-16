package org.example.client;

import java.util.List;

/**
 * Classe wrapper pour faciliter l'interaction avec le service web AbsenceService.
 * Cette classe encapsule la logique de connexion au service et fournit des méthodes
 * simplifiées pour les opérations CRUD.
 */
public class AbsenceServiceClient {

    private final AbsenceService service;

    /**
     * Constructeur - Initialise la connexion au service web
     */
    public AbsenceServiceClient() {
        AbsenceServiceImplService serviceFactory = new AbsenceServiceImplService();
        this.service = serviceFactory.getAbsenceServiceImplPort();
    }

    /**
     * Ajoute un nouvel étudiant
     * @param etudiant L'étudiant à ajouter
     */
    public void ajouterEtudiant(Etudiant etudiant) {
        service.add(etudiant);
    }

    /**
     * Met à jour les informations d'un étudiant existant
     * @param etudiant L'étudiant avec les nouvelles informations
     */
    public void mettreAJourEtudiant(Etudiant etudiant) {
        service.update(etudiant);
    }

    /**
     * Supprime un étudiant par son ID
     * @param id L'identifiant de l'étudiant
     */
    public void supprimerEtudiant(long id) {
        service.delete(id);
    }

    /**
     * Récupère le taux d'absence d'un étudiant
     * Cette méthode est centrale et peut être utilisée par d'autres services
     * (comme le service de gestion des notes) pour obtenir le taux T
     *
     * @param id L'identifiant de l'étudiant
     * @return Le taux d'absence en pourcentage (0-100)
     */
    public double obtenirTauxAbsence(long id) {
        return service.read(id);
    }

    /**
     * Génère la liste noire des étudiants dépassant un seuil d'absence
     * La liste est triée par heures d'absence puis par nom
     *
     * @param seuil Le seuil d'absence en pourcentage
     * @return Liste des étudiants dépassant le seuil
     */
    public List<Etudiant> genererListeNoire(int seuil) {
        return service.blackListCreate(seuil);
    }

    /**
     * Récupère la liste de tous les étudiants
     * Utilise blackListCreate avec un seuil de 0 pour obtenir tous les étudiants
     *
     * @return Liste de tous les étudiants
     */
    public List<Etudiant> obtenirTousLesEtudiants() {
        return service.blackListCreate(0);
    }

    /**
     * Crée un nouvel objet Etudiant avec les informations fournies
     *
     * @param id L'identifiant de l'étudiant
     * @param cne Le code national de l'étudiant
     * @param nom Le nom de l'étudiant
     * @param prenom Le prénom de l'étudiant
     * @param niveau Le niveau d'études
     * @param heuresAbsence Le nombre d'heures d'absence
     * @return Un nouvel objet Etudiant
     */
    public Etudiant creerEtudiant(long id, String cne, String nom, String prenom, String niveau, int heuresAbsence) {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setCne(cne);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setNiveau(niveau);
        etudiant.setNombreHeuresAbsence(heuresAbsence);
        return etudiant;
    }
}

