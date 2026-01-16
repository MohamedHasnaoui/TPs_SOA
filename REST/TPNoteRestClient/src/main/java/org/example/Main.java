package org.example;

import org.example.ui.MainFrame;
import javax.swing.SwingUtilities;

/**
 * Point d'entrée de l'application client REST pour la gestion des notes.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
