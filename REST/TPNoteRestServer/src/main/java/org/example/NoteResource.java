package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Resource REST pour la gestion des notes d'étudiants.
 * Expose les opérations CRUD et la calcul de note finale avec prise en compte
 * de l'absence.
 */
@Path("/notes")
public class NoteResource {

    private static final Map<String, Etudiant> etudiants = new ConcurrentHashMap<>();
    private static final int MAX_CAPACITY = 20;

    static {
        // Données de test avec les mêmes CNE que le service d'absence
        etudiants.put("G123456", new Etudiant("Dupont", "Jean", "G123456", 14, 16));
        etudiants.put("G789012", new Etudiant("Durand", "Marie", "G789012", 10, 11));
        etudiants.put("G345678", new Etudiant("Martin", "Paul", "G345678", 18, 18));
        etudiants.put("G999999", new Etudiant("Petit", "Lucie", "G999999", 8, 9));
    }

    /**
     * Ajoute un nouvel étudiant.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getCne() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"CNE est obligatoire\"}")
                    .build();
        }
        if (etudiants.size() >= MAX_CAPACITY) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"Capacité maximale atteinte\"}")
                    .build();
        }
        if (etudiants.containsKey(etudiant.getCne())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"Étudiant déjà existant\"}")
                    .build();
        }
        etudiants.put(etudiant.getCne(), etudiant);
        URI location = URI.create("/notes/" + etudiant.getCne());
        return Response.created(location).entity(etudiant).build();
    }

    /**
     * Récupère tous les étudiants.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {
        return Response.ok(new ArrayList<>(etudiants.values())).build();
    }

    /**
     * Récupère un étudiant par son CNE.
     */
    @GET
    @Path("/{cne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiant(@PathParam("cne") String cne) {
        Etudiant etudiant = etudiants.get(cne);
        if (etudiant == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Étudiant non trouvé\"}")
                    .build();
        }
        return Response.ok(etudiant).build();
    }

    /**
     * Récupère la note moyenne d'un étudiant par son nom.
     */
    @GET
    @Path("/note/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteByNom(@PathParam("nom") String nom) {
        Etudiant etudiant = etudiants.values().stream()
                .filter(e -> e.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);

        if (etudiant == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Étudiant non trouvé\"}")
                    .build();
        }
        return Response.ok("{\"nom\": \"" + etudiant.getNom() + "\", \"moyenne\": " + etudiant.getMoyenne() + "}")
                .build();
    }

    /**
     * Récupère les étudiants qui ont validé le module (moyenne >= 12).
     */
    @GET
    @Path("/validants")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantsValidant() {
        List<Etudiant> validants = etudiants.values().stream()
                .filter(e -> e.getMoyenne() >= 12)
                .collect(Collectors.toList());
        return Response.ok(validants).build();
    }

    /**
     * Récupère le(s) majorant(s) - étudiants avec la meilleure note.
     */
    @GET
    @Path("/majorants")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMajorant() {
        if (etudiants.isEmpty()) {
            return Response.ok(new ArrayList<>()).build();
        }
        double maxNote = etudiants.values().stream()
                .mapToDouble(Etudiant::getMoyenne)
                .max()
                .orElse(0);
        List<Etudiant> majorants = etudiants.values().stream()
                .filter(e -> e.getMoyenne() == maxNote)
                .collect(Collectors.toList());
        return Response.ok(majorants).build();
    }

    /**
     * Récupère tous les étudiants triés par note (décroissant).
     */
    @GET
    @Path("/tries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantsTries() {
        List<Etudiant> tries = etudiants.values().stream()
                .sorted(Comparator.comparingDouble(Etudiant::getMoyenne).reversed())
                .collect(Collectors.toList());
        return Response.ok(tries).build();
    }

    /**
     * Calcule la note finale avec prise en compte de l'absence.
     * Formule: N = M - T*M où:
     * - N = note finale
     * - M = moyenne des notes
     * - T = taux d'absence (obtenu du service d'absence REST)
     */
    @GET
    @Path("/noteFinal/{cne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteFinalAvecAbsence(@PathParam("cne") String cne) {
        Etudiant etudiant = etudiants.get(cne);
        if (etudiant == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Étudiant non trouvé dans le service de notes\"}")
                    .build();
        }

        double M = etudiant.getMoyenne(); // Moyenne des notes

        // Appeler le service d'absence REST pour obtenir le taux d'absence
        double T = AbsenceServiceClient.getTauxAbsence(cne);

        // Appliquer la formule: N = M - T*M = M(1-T)
        double N = M - (T * M);
        N = Math.max(0, N); // La note ne peut pas être négative

        NoteFinalResponse response = new NoteFinalResponse(
                cne,
                etudiant.getNom(),
                etudiant.getPrenom(),
                M,
                T,
                N);

        return Response.ok(response).build();
    }

    /**
     * Met à jour un étudiant.
     */
    @PUT
    @Path("/{cne}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("cne") String cne, Etudiant etudiant) {
        if (!etudiants.containsKey(cne)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Étudiant non trouvé\"}")
                    .build();
        }
        etudiant.setCne(cne);
        etudiants.put(cne, etudiant);
        return Response.ok(etudiant).build();
    }

    /**
     * Supprime un étudiant.
     */
    @DELETE
    @Path("/{cne}")
    public Response deleteEtudiant(@PathParam("cne") String cne) {
        if (etudiants.remove(cne) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
