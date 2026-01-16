package org.example.ui;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Etudiant;
import org.example.NoteFinalResponse;

import java.util.List;

/**
 * Client REST pour le service de gestion des notes.
 */
public class NoteServiceClient {
    private static final String BASE_URI = System.getenv().getOrDefault("NOTE_API_BASE_URI",
            "http://localhost:8086/api");
    private final Client client;
    private final WebTarget target;

    public NoteServiceClient() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(BASE_URI).path("notes");
    }

    /**
     * Récupère tous les étudiants.
     */
    public List<Etudiant> getAllEtudiants() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Etudiant>>() {
        });
    }

    /**
     * Récupère un étudiant par son CNE.
     */
    public Etudiant getEtudiant(String cne) {
        Response response = target.path(cne).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return null;
        }
        return response.readEntity(Etudiant.class);
    }

    /**
     * Ajoute un nouvel étudiant.
     */
    public Response addEtudiant(Etudiant etudiant) {
        return target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(etudiant, MediaType.APPLICATION_JSON));
    }

    /**
     * Met à jour un étudiant.
     */
    public Response updateEtudiant(String cne, Etudiant etudiant) {
        return target.path(cne).request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(etudiant, MediaType.APPLICATION_JSON));
    }

    /**
     * Supprime un étudiant.
     */
    public Response deleteEtudiant(String cne) {
        return target.path(cne).request().delete();
    }

    /**
     * Récupère les étudiants qui ont validé le module.
     */
    public List<Etudiant> getEtudiantsValidant() {
        Response response = target.path("validants").request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Etudiant>>() {
        });
    }

    /**
     * Récupère le(s) majorant(s).
     */
    public List<Etudiant> getMajorants() {
        Response response = target.path("majorants").request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Etudiant>>() {
        });
    }

    /**
     * Récupère tous les étudiants triés par note.
     */
    public List<Etudiant> getEtudiantsTries() {
        Response response = target.path("tries").request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Etudiant>>() {
        });
    }

    /**
     * Calcule la note finale avec prise en compte de l'absence.
     * Cette méthode appelle le service de notes qui communique avec le service
     * d'absence.
     */
    public NoteFinalResponse getNoteFinalAvecAbsence(String cne) {
        Response response = target.path("noteFinal").path(cne).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return null;
        }
        return response.readEntity(NoteFinalResponse.class);
    }

    public void close() {
        client.close();
    }
}
