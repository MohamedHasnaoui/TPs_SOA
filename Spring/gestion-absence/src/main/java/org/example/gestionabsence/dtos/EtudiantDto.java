package org.example.gestionabsence.dtos;

public class EtudiantDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String cne;
    private String niveau;
    private double heuresAbsence;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }
    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }
    public double getHeuresAbsence() { return heuresAbsence; }
    public void setHeuresAbsence(double heuresAbsence) { this.heuresAbsence = heuresAbsence; }
}

