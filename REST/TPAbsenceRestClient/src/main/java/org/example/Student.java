package org.example;

public class Student {
    private String cne;
    private String nom;
    private String prenom;
    private String niveau;
    private double absenceHours;

    public Student() {
    }

    public Student(String cne, String nom, String prenom, String niveau, double absenceHours) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
        this.absenceHours = absenceHours;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public double getAbsenceHours() {
        return absenceHours;
    }

    public void setAbsenceHours(double absenceHours) {
        this.absenceHours = absenceHours;
    }

    @Override
    public String toString() {
        return "Student{"
                + "cne='" + cne + "'\''" +
                ", nom='" + nom + "'\''" +
                ", prenom='" + prenom + "'\''" +
                ", niveau='" + niveau + "'\''" +
                ", absenceHours=" + absenceHours +
                '}';
    }
}
