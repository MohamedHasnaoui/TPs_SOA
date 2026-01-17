package org.example.gestionabsencespringboot.controller;

import org.example.gestionabsencespringboot.entity.Etudiant;
import org.example.gestionabsencespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    /**
     * Obtenir tous les etudiants
     */
    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantRepository.findAll());
    }

    /**
     * Obtenir un etudiant par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Ajouter un etudiant
     */
    @PostMapping
    public ResponseEntity<Etudiant> ajouterEtudiant(@RequestBody Etudiant etudiant) {
        try {
            // Verifier si un etudiant avec ce CNE existe deja
            if (etudiantRepository.existsByCne(etudiant.getCne())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            Etudiant saved = etudiantRepository.save(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Mettre a jour un etudiant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        if (!etudiantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        etudiant.setId(id);
        Etudiant updated = etudiantRepository.save(etudiant);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprimer un etudiant
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        if (!etudiantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        etudiantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtenir la liste noire (etudiants avec taux d'absence >= 50%)
     */
    @GetMapping("/blacklist")
    public ResponseEntity<List<Etudiant>> getBlacklist(
            @RequestParam(defaultValue = "50.0") Double tauxSeuil) {
        List<Etudiant> blacklist = etudiantRepository.findBlackListByTauxAbsence(tauxSeuil);
        return ResponseEntity.ok(blacklist);
    }

    /**
     * Obtenir le taux d'absence d'un etudiant
     */
    @GetMapping("/{id}/taux-absence")
    public ResponseEntity<Double> getTauxAbsence(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(e -> ResponseEntity.ok(e.getTauxAbsence()))
                .orElse(ResponseEntity.notFound().build());
    }
}
