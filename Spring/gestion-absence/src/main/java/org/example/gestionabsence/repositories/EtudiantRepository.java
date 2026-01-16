package org.example.gestionabsence.repositories;

import org.example.gestionabsence.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    List<Etudiant> findByHeuresAbsenceGreaterThanEqualOrderByHeuresAbsenceDescNomAsc(Double heuresSeuil);
}