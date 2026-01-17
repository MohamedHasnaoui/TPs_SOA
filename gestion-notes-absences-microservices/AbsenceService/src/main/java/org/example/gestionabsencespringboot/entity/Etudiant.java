package org.example.gestionabsencespringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String cne;

    @Column(nullable = false)
    private String niveau;

    @Column(nullable = false)
    private Integer heuresAbsence;

    // Constructor sans l'id pour faciliter la creation
    public Etudiant(String nom, String prenom, String cne, String niveau, Integer heuresAbsence) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.niveau = niveau;
        this.heuresAbsence = heuresAbsence;
    }

    // Methode pour calculer le taux d'absence (sur 500 heures par an)
    public double getTauxAbsence() {
        return (heuresAbsence / 500.0) * 100;
    }

    // Methode pour verifier si l'etudiant est en liste noire (taux > 50%)
    public boolean isListeNoire() {
        return getTauxAbsence() >= 50.0;
    }
}
