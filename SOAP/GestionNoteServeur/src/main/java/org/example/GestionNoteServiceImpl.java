package org.example;

import jakarta.jws.WebService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.example.GestionNoteService")
public class GestionNoteServiceImpl implements GestionNoteService {
    private final List<Etudiant> etudiants = new ArrayList<>();
    private static final int MAX_CAPACITY = 20; // Capacité maximale du tableau d'étudiants
    private static final String ABSENCE_SERVICE_URL = "http://localhost:8090/absenceService?wsdl";

    public GestionNoteServiceImpl() {
        // Initialisation avec quelques données pour les tests (avec CNE)
        etudiants.add(new Etudiant("Doe", "John", "C123", 14, 16));
        etudiants.add(new Etudiant("Smith", "Jane", "C456", 10, 11));
        etudiants.add(new Etudiant("Brown", "Charlie", "C789", 18, 18));
        etudiants.add(new Etudiant("Petit", "Lucie", "C999", 8, 9));
    }

    @Override
    public boolean ajouterEtudiant(Etudiant etudiant) {
        if (etudiants.size() >= MAX_CAPACITY) {
            return false; // Le tableau est plein
        }
        return etudiants.add(etudiant);
    }

    @Override
    public double getNote(String nom) {
        return etudiants.stream()
                .filter(e -> e.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .map(Etudiant::getMoyenne)
                .orElse(-1.0); // Retourne -1 si l'étudiant n'est pas trouvé
    }

    @Override
    public List<Etudiant> getEtudiantsValidant() {
        return etudiants.stream()
                .filter(e -> e.getMoyenne() >= 12)
                .collect(Collectors.toList());
    }

    @Override
    public List<Etudiant> getMajorant() {
        if (etudiants.isEmpty()) {
            return new ArrayList<>();
        }
        double maxNote = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .max()
                .orElse(0);
        return etudiants.stream()
                .filter(e -> e.getMoyenne() == maxNote)
                .collect(Collectors.toList());
    }

    @Override
    public List<Etudiant> getEtudiantsTries() {
        return etudiants.stream()
                .sorted(Comparator.comparingDouble(Etudiant::getMoyenne).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Calcule la note finale avec prise en compte de l'absence.
     * Formule: N = M - T*M
     * où N = note finale, M = moyenne, T = taux d'absence
     */
    @Override
    public double getNoteFinalAvecAbsence(String cne) {
        // Trouver l'étudiant par CNE
        Etudiant etudiant = etudiants.stream()
                .filter(e -> e.getCne() != null && e.getCne().equals(cne))
                .findFirst()
                .orElse(null);

        if (etudiant == null) {
            return -1.0; // Étudiant non trouvé
        }

        double M = etudiant.getMoyenne(); // Moyenne des notes

        // Appeler le service d'absence pour obtenir le taux d'absence
        double T = getTauxAbsenceFromService(cne);

        // Appliquer la formule: N = M - T*M = M(1-T)
        double N = M - (T * M);

        return Math.max(0, N); // La note ne peut pas être négative
    }

    /**
     * Appelle le service d'absence pour récupérer le taux d'absence d'un étudiant
     */
    private double getTauxAbsenceFromService(String cne) {
        try {
            URL wsdlURL = URI.create(ABSENCE_SERVICE_URL).toURL();
            // Namespace basé sur le package org.example.service (inversé en URL)
            QName serviceName = new QName("http://service.example.org/", "AbsenceServiceService");
            Service service = Service.create(wsdlURL, serviceName);

            QName portName = new QName("http://service.example.org/", "AbsenceServicePort");
            AbsenceServiceClient absenceService = service.getPort(portName, AbsenceServiceClient.class);

            return absenceService.getTauxAbsenceByCne(cne);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel au service d'absence: " + e.getMessage());
            return 0; // En cas d'erreur, retourner 0 (pas de pénalité)
        }
    }
}
