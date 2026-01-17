package org.example.gestionabsencespringboot.repository;

import org.example.gestionabsencespringboot.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Trouver un etudiant par CNE
    Optional<Etudiant> findByCne(String cne);

    // Verifier si un CNE existe
    boolean existsByCne(String cne);

    // Trouver les etudiants en liste noire (taux d'absence >= 50%)
    @Query("SELECT e FROM Etudiant e WHERE (e.heuresAbsence / 500.0) * 100 >= :tauxSeuil ORDER BY e.heuresAbsence DESC")
    List<Etudiant> findBlackListByTauxAbsence(@Param("tauxSeuil") Double tauxSeuil);

    // Trouver les etudiants tries par heures d'absence decroissant
    List<Etudiant> findAllByOrderByHeuresAbsenceDesc();
}
