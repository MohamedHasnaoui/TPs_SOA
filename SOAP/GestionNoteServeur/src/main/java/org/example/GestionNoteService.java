package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface GestionNoteService {

    @WebMethod
    boolean ajouterEtudiant(@WebParam(name = "etudiant") Etudiant etudiant);

    @WebMethod
    double getNote(@WebParam(name = "nom") String nom);

    @WebMethod
    List<Etudiant> getEtudiantsValidant();

    @WebMethod
    List<Etudiant> getMajorant();

    @WebMethod
    List<Etudiant> getEtudiantsTries();

    /**
     * Calcule la note finale en prenant en compte le taux d'absence.
     * Formule: N = M - T*M où:
     * - N = note finale du module
     * - M = moyenne des notes obtenues dans le module
     * - T = taux d'absence de l'étudiant dans le module
     * 
     * @param cne le CNE de l'étudiant
     * @return la note finale après pénalité d'absence
     */
    @WebMethod
    double getNoteFinalAvecAbsence(@WebParam(name = "cne") String cne);
}
