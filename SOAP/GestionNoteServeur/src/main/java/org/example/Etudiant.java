package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Etudiant {
    private String nom;
    private String prenom;
    private String cne; // CNE pour identifier l'étudiant dans le service d'absence
    private double note1;
    private double note2;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, double note1, double note2) {
        this.nom = nom;
        this.prenom = prenom;
        this.note1 = note1;
        this.note2 = note2;
    }

    public Etudiant(String nom, String prenom, String cne, double note1, double note2) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.note1 = note1;
        this.note2 = note2;
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

    public double getNote1() {
        return note1;
    }

    public void setNote1(double note1) {
        this.note1 = note1;
    }

    public double getNote2() {
        return note2;
    }

    public void setNote2(double note2) {
        this.note2 = note2;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public double getMoyenne() {
        return (note1 + note2) / 2;
    }
}
