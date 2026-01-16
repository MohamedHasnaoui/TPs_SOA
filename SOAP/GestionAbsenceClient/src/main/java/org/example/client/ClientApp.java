package org.example.client;

import javax.swing.*;

/**
 * Point d'entrée de l'application cliente pour la gestion de l'absentéisme.
 * Cette classe lance l'interface graphique Swing sur l'Event Dispatch Thread (EDT).
 */
public class ClientApp {

    public static void main(String[] args) {
        // Lancer l'interface graphique sur l'Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                GestionAbsenceUI frame = new GestionAbsenceUI();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Erreur lors du démarrage de l'application : " + e.getMessage() +
                    "\n\nAssurez-vous que le service web est démarré sur http://localhost:8090/absenceService",
                    "Erreur de démarrage",
                    JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        });
    }
}

