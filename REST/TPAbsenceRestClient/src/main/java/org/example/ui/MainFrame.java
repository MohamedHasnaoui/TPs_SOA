package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Gestion de l'Absentéisme des Étudiants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        JTabbedPane tabbedPane = new JTabbedPane();

        // The panels will be created in separate classes
        JPanel studentPanel = new StudentPanel(); 
        JPanel blacklistPanel = new BlacklistPanel(); 

        tabbedPane.addTab("Gestion des Étudiants", studentPanel);
        tabbedPane.addTab("Liste Noire", blacklistPanel);

        add(tabbedPane);

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame
    }
}
