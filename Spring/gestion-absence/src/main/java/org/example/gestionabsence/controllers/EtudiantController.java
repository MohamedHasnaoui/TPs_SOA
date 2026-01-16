package org.example.gestionabsence.controllers;

import org.example.gestionabsence.dtos.EtudiantDto;
import org.example.gestionabsence.services.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public ResponseEntity<EtudiantDto> addEtudiant(@RequestBody EtudiantDto etudiantDto) {
        return new ResponseEntity<>(etudiantService.addEtudiant(etudiantDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EtudiantDto>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDto> getEtudiantById(@PathVariable Integer id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @GetMapping("/{id}/taux-absence")
    public ResponseEntity<Map<String, Double>> getTauxAbsence(@PathVariable Integer id) {
        double taux = etudiantService.getTauxAbsence(id);
        return ResponseEntity.ok(Map.of("tauxAbsence", taux));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDto> updateEtudiant(@PathVariable Integer id, @RequestBody EtudiantDto etudiantDto) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, etudiantDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Integer id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/blacklist")
    public ResponseEntity<List<EtudiantDto>> getBlacklist(@RequestParam(defaultValue = "50.0") double seuil) {
        return ResponseEntity.ok(etudiantService.createBlacklist(seuil));
    }
}
