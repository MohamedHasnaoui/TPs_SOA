package org.example;

/**
 * Classe représentant la réponse pour la note finale avec prise en compte de
 * l'absence.
 */
public class NoteFinalResponse {
    private String cne;
    private String nom;
    private String prenom;
    private double moyenne;
    private double tauxAbsence;
    private double noteFinal;

    public NoteFinalResponse() {
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public double getTauxAbsence() {
        return tauxAbsence;
    }

    public void setTauxAbsence(double tauxAbsence) {
        this.tauxAbsence = tauxAbsence;
    }

    public double getNoteFinal() {
        return noteFinal;
    }

    public void setNoteFinal(double noteFinal) {
        this.noteFinal = noteFinal;
    }
}
