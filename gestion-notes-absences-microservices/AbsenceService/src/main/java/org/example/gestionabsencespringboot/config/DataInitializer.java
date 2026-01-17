package org.example.gestionabsencespringboot.config;

import org.example.gestionabsencespringboot.entity.Etudiant;
import org.example.gestionabsencespringboot.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EtudiantRepository repository) {
        return args -> {
            // Verifier si la base de donnees est vide avant d'initialiser
            if (repository.count() == 0) {
                // Initialiser quelques etudiants pour tester
                repository.save(new Etudiant("Dupont", "Jean", "CNE001", "L3", 50));
                repository.save(new Etudiant("Martin", "Marie", "CNE002", "M1", 120));
                repository.save(new Etudiant("Bernard", "Pierre", "CNE003", "L2", 300));
                repository.save(new Etudiant("Dubois", "Sophie", "CNE004", "L3", 450));
                repository.save(new Etudiant("Thomas", "Luc", "CNE005", "M2", 80));
                repository.save(new Etudiant("Robert", "Julie", "CNE006", "L1", 250));
                repository.save(new Etudiant("Petit", "Paul", "CNE007", "L3", 150));

                System.out.println("Base de donnees initialisee avec " + repository.count() + " etudiants");
            } else {
                System.out.println("Base de donnees deja initialisee avec " + repository.count() + " etudiants");
            }
        };
    }
}
