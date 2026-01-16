package org.example.service;

import org.example.model.Etudiant;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface AbsenceService {

    @WebMethod
    void add(Etudiant etudiant);

    @WebMethod
    double read(long etudiantId);

    @WebMethod
    void update(Etudiant etudiant);

    @WebMethod
    void delete(long etudiantId);

    @WebMethod
    List<Etudiant> blackListCreate(int seuil);

    @WebMethod
    double getTauxAbsenceByCne(String cne);
}
