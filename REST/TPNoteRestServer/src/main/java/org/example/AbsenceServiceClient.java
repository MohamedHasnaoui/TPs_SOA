package org.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Client REST pour appeler le service d'absence.
 * Permet de récupérer le taux d'absence d'un étudiant par son CNE.
 */
public class AbsenceServiceClient {
    private static final String ABSENCE_SERVICE_URL = "http://localhost:8085/api/students";
    private static final double TOTAL_HOURS = 100.0; // Total des heures du module

    /**
     * Récupère le taux d'absence d'un étudiant par son CNE.
     * 
     * @param cne le CNE de l'étudiant
     * @return le taux d'absence (entre 0 et 1), ou 0 si l'étudiant n'est pas trouvé
     */
    public static double getTauxAbsence(String cne) {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ABSENCE_SERVICE_URL).path(cne);

            Response response = target.request(MediaType.APPLICATION_JSON).get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                // On lit les heures d'absence depuis la réponse JSON
                AbsenceStudent student = response.readEntity(AbsenceStudent.class);
                if (student != null) {
                    return student.getAbsenceHours() / TOTAL_HOURS;
                }
            }
            return 0; // Étudiant non trouvé ou erreur, pas de pénalité
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel au service d'absence: " + e.getMessage());
            return 0; // En cas d'erreur, retourner 0 (pas de pénalité)
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * Classe interne pour mapper la réponse du service d'absence.
     */
    public static class AbsenceStudent {
        private String cne;
        private String nom;
        private String prenom;
        private String niveau;
        private double absenceHours;

        public AbsenceStudent() {
        }

        public String getCne() {
            return cne;
        }

        public void setCne(String cne) {
            this.cne = cne;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getNiveau() {
            return niveau;
        }

        public void setNiveau(String niveau) {
            this.niveau = niveau;
        }

        public double getAbsenceHours() {
            return absenceHours;
        }

        public void setAbsenceHours(double absenceHours) {
            this.absenceHours = absenceHours;
        }
    }
}
