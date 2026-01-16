package org.example.gestionabsence.entities;

import jakarta.persistence.*;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "cne", nullable = false, unique = true)
    private String cne;

    @Column(name = "niveau")
    private String niveau;

    @Column(name = "heures_absence")
    private double heuresAbsence;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String cne, String niveau, double heuresAbsence) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.niveau = niveau;
        this.heuresAbsence = heuresAbsence;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public double getHeuresAbsence() {
        return heuresAbsence;
    }

    public void setHeuresAbsence(double heuresAbsence) {
        this.heuresAbsence = heuresAbsence;
    }
}
