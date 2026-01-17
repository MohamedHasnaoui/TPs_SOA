package org.example.gestionnotesspringboot.repositories;

import org.example.gestionnotesspringboot.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByCne(String cne);

    Optional<Etudiant> findByNom(String nom);

    boolean existsByCne(String cne);

    // Trouver les etudiants qui ont valide (moyenne >= 12)
    @Query("SELECT e FROM Etudiant e WHERE (e.noteCC * 0.3 + e.noteTP * 0.2 + e.noteExam * 0.5) >= 12")
    List<Etudiant> findEtudiantsValidant();

    // Trouver les majorants (meilleure moyenne)
    @Query("SELECT e FROM Etudiant e WHERE (e.noteCC * 0.3 + e.noteTP * 0.2 + e.noteExam * 0.5) = " +
            "(SELECT MAX(e2.noteCC * 0.3 + e2.noteTP * 0.2 + e2.noteExam * 0.5) FROM Etudiant e2)")
    List<Etudiant> findMajorants();

    // Tous les etudiants tries par moyenne decroissante
    @Query("SELECT e FROM Etudiant e ORDER BY (e.noteCC * 0.3 + e.noteTP * 0.2 + e.noteExam * 0.5) DESC")
    List<Etudiant> findAllOrderByMoyenneDesc();
}
