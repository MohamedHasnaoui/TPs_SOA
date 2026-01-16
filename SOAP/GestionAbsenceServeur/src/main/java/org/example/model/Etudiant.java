package org.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Etudiant {
    private static long idCounter = 0;
    private long id;
    private String nom;
    private String prenom;
    private String cne;
    private String niveau;
    private int nombreHeuresAbsence;

    public Etudiant() {
        this.id = ++idCounter;
    }

    public Etudiant(String nom, String prenom, String cne, String niveau, int nombreHeuresAbsence) {
        this.id = ++idCounter;
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.niveau = niveau;
        this.nombreHeuresAbsence = nombreHeuresAbsence;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getNombreHeuresAbsence() {
        return nombreHeuresAbsence;
    }

    public void setNombreHeuresAbsence(int nombreHeuresAbsence) {
        this.nombreHeuresAbsence = nombreHeuresAbsence;
    }
}
