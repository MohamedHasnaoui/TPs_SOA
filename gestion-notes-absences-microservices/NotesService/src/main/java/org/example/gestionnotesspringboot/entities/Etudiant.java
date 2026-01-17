package org.example.gestionnotesspringboot.entities;

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

    @Column(unique = true, nullable = false, name = "cne")
    private String cne; // Code National Etudiant

    @Column(nullable = false)
    private String niveau; // ex: "L1", "L2", "L3", "M1", "M2"

    @Column
    private Double noteCC; // Note de Controle Continu

    @Column
    private Double noteTP; // Note de Travaux Pratiques

    @Column
    private Double noteExam; // Note d'Examen

    // Calcul de la moyenne (CC: 30%, TP: 20%, Exam: 50%)
    public Double getMoyenne() {
        if (noteCC == null || noteTP == null || noteExam == null) {
            return null;
        }
        return (noteCC * 0.3) + (noteTP * 0.2) + (noteExam * 0.5);
    }

    // Calcul de la note finale avec le taux d'absence
    // Formule: N = M - T*M = M*(1-T)
    public Double getNoteFinale(Double tauxAbsence) {
        Double moyenne = getMoyenne();
        if (moyenne == null || tauxAbsence == null) {
            return moyenne;
        }
        return moyenne * (1 - tauxAbsence / 100);
    }

}
