package org.example.gestionabsence.services;

import org.example.gestionabsence.dtos.EtudiantDto;
import org.example.gestionabsence.entities.Etudiant;
import org.example.gestionabsence.repositories.EtudiantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantService {

    // Supposons un total de 100 heures de cours pour le calcul du taux.
    private static final double TOTAL_HEURES_COURS = 100.0;

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public EtudiantDto addEtudiant(EtudiantDto etudiantDto) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(etudiantDto, etudiant);
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        BeanUtils.copyProperties(savedEtudiant, etudiantDto);
        return etudiantDto;
    }

    public double getTauxAbsence(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
        if (TOTAL_HEURES_COURS == 0) return 0;
        return (etudiant.getHeuresAbsence() / TOTAL_HEURES_COURS) * 100;
    }

    public EtudiantDto updateEtudiant(Integer id, EtudiantDto etudiantDto) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
        BeanUtils.copyProperties(etudiantDto, etudiant, "id");
        Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
        BeanUtils.copyProperties(updatedEtudiant, etudiantDto);
        return etudiantDto;
    }

    public void deleteEtudiant(Integer id) {
        etudiantRepository.deleteById(id);
    }

    public List<EtudiantDto> getAllEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EtudiantDto getEtudiantById(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'id " + id));
        return convertToDto(etudiant);
    }

    public List<EtudiantDto> createBlacklist(double seuilTaux) {
        double heuresSeuil = (seuilTaux * TOTAL_HEURES_COURS) / 100.0;
        List<Etudiant> etudiants = etudiantRepository.findByHeuresAbsenceGreaterThanEqualOrderByHeuresAbsenceDescNomAsc(heuresSeuil);
        return etudiants.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EtudiantDto convertToDto(Etudiant etudiant) {
        EtudiantDto etudiantDto = new EtudiantDto();
        BeanUtils.copyProperties(etudiant, etudiantDto);
        return etudiantDto;
    }
}
