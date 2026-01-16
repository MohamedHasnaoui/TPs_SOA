package org.example.ws;

import org.example.model.Etudiant;
import org.example.service.AbsenceService;

import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.example.service.AbsenceService")
public class AbsenceServiceImpl implements AbsenceService {
    private static final int TOTAL_HOURS = 100; // Assuming a total of 100 hours for the module
    private static final List<Etudiant> etudiants = new ArrayList<>();

    public AbsenceServiceImpl() {
        // Initialize with some data
        etudiants.add(new Etudiant("Doe", "John", "C123", "GLSID", 10));
        etudiants.add(new Etudiant("Smith", "Jane", "C456", "GLSID", 25));
        etudiants.add(new Etudiant("Brown", "Charlie", "C789", "GLSID", 5));
    }

    @Override
    public void add(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    @Override
    public double read(long etudiantId) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId() == etudiantId) {
                return (double) etudiant.getNombreHeuresAbsence() / TOTAL_HOURS;
            }
        }
        return 0;
    }

    @Override
    public void update(Etudiant etudiant) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getId() == etudiant.getId()) {
                etudiants.set(i, etudiant);
                return;
            }
        }
    }

    @Override
    public void delete(long etudiantId) {
        etudiants.removeIf(etudiant -> etudiant.getId() == etudiantId);
    }

    @Override
    public List<Etudiant> blackListCreate(int seuil) {
        double seuilTaux = (double) seuil / 100;
        return etudiants.stream()
                .filter(e -> ((double) e.getNombreHeuresAbsence() / TOTAL_HOURS) >= seuilTaux)
                .sorted((e1, e2) -> {
                    if (e1.getNombreHeuresAbsence() != e2.getNombreHeuresAbsence()) {
                        return Integer.compare(e2.getNombreHeuresAbsence(), e1.getNombreHeuresAbsence());
                    }
                    return e1.getNom().compareTo(e2.getNom());
                })
                .collect(Collectors.toList());
    }

    @Override
    public double getTauxAbsenceByCne(String cne) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getCne().equals(cne)) {
                return (double) etudiant.getNombreHeuresAbsence() / TOTAL_HOURS;
            }
        }
        return 0;
    }
}
