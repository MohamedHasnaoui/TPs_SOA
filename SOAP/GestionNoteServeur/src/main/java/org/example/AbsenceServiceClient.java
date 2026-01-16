package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

/**
 * Interface client pour appeler le service d'absence.
 * Cette interface doit correspondre à l'interface du service distant.
 */
@WebService(targetNamespace = "http://service.example.org/")
public interface AbsenceServiceClient {

    /**
     * Récupère le taux d'absence d'un étudiant par son CNE.
     * 
     * @param cne le CNE de l'étudiant
     * @return le taux d'absence (entre 0 et 1)
     */
    @WebMethod
    double getTauxAbsenceByCne(@WebParam(name = "arg0") String cne);
}
