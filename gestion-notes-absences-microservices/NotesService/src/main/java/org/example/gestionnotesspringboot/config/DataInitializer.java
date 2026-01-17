package org.example.gestionnotesspringboot.config;

import org.example.gestionnotesspringboot.entities.Etudiant;
import org.example.gestionnotesspringboot.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EtudiantRepository etudiantRepository) {
        return args -> {
            // Verifier si la base de donnees est vide
            if (etudiantRepository.count() == 0) {
                // Ajouter des etudiants de test avec notes (id, nom, prenom, cne, niveau,
                // noteCC, noteTP, noteExam)
                etudiantRepository.save(new Etudiant(null, "Alami", "Ahmed", "CNE001", "L3", 14.0, 15.0, 16.0));
                etudiantRepository.save(new Etudiant(null, "Benjelloun", "Fatima", "CNE002", "M1", 12.0, 13.0, 11.0));
                etudiantRepository.save(new Etudiant(null, "Chakir", "Omar", "CNE003", "L2", 8.0, 10.0, 9.0));
                etudiantRepository.save(new Etudiant(null, "Darif", "Sara", "CNE004", "L3", 16.0, 17.0, 18.0));
                etudiantRepository.save(new Etudiant(null, "El Amrani", "Karim", "CNE005", "M2", 13.0, 14.0, 12.0));
                etudiantRepository.save(new Etudiant(null, "Fassi", "Leila", "CNE006", "L1", 10.0, 11.0, 10.0));
                etudiantRepository.save(new Etudiant(null, "Ghali", "Hassan", "CNE007", "L3", 15.0, 16.0, 14.0));
                etudiantRepository.save(new Etudiant(null, "Hamidi", "Nadia", "CNE008", "M1", 11.0, 12.0, 13.0));

                System.out.println("Donnees de test initialisees avec succes!");
            }
        };
    }
}
