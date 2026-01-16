package org.example.ui;

import org.example.Etudiant;
import org.example.NoteFinalResponse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interface graphique Swing pour la gestion des notes d'étudiants (REST).
 * Consomme un service REST JAX-RS pour effectuer les opérations CRUD.
 */
public class MainFrame extends JFrame {
    // Composants de l'interface
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtCne;
    private JTextField txtNote1;
    private JTextField txtNote2;
    private JTable tableEtudiants;
    private DefaultTableModel tableModel;
    private JButton btnAjouter;
    private JButton btnAfficherTous;
    private JButton btnAfficherValidants;
    private JButton btnAfficherMajorants;
    private JTextField txtRechercheCne;
    private JButton btnRechercherNoteAvecAbsence;

    // Client REST
    private NoteServiceClient serviceClient;

    public MainFrame() {
        // Initialiser le client REST
        serviceClient = new NoteServiceClient();

        // Configurer le Look and Feel
        setLookAndFeel();

        // Configurer la fenêtre principale
        setTitle("Gestion des Notes - REST Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLayout(new BorderLayout(10, 10));

        // Créer et ajouter les panneaux
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Impossible de charger le Look and Feel Nimbus");
        }
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        northPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        northPanel.add(createAddStudentPanel());
        northPanel.add(createSearchWithAbsencePanel());

        return northPanel;
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Ajouter un étudiant",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 102, 204)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nom
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNom = new JTextField(15);
        panel.add(txtNom, gbc);

        // Prénom
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPrenom = new JTextField(15);
        panel.add(txtPrenom, gbc);

        // CNE
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("CNE:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCne = new JTextField(15);
        panel.add(txtCne, gbc);

        // Note 1
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Note 1:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNote1 = new JTextField(15);
        panel.add(txtNote1, gbc);

        // Note 2
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Note 2:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNote2 = new JTextField(15);
        panel.add(txtNote2, gbc);

        // Bouton Ajouter
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAjouter.addActionListener(new AddStudentListener());
        panel.add(btnAjouter, gbc);

        return panel;
    }

    private JPanel createSearchWithAbsencePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Note finale (avec absence) - REST",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(204, 102, 0)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CNE
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("CNE:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtRechercheCne = new JTextField(15);
        panel.add(txtRechercheCne, gbc);

        // Info formule
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JLabel lblFormule = new JLabel("Formule: N = M - T×M (appel REST au service Absence)");
        lblFormule.setFont(new Font("Arial", Font.ITALIC, 10));
        lblFormule.setForeground(Color.GRAY);
        panel.add(lblFormule, gbc);

        // Bouton Calculer
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        btnRechercherNoteAvecAbsence = new JButton("Calculer Note Finale");
        btnRechercherNoteAvecAbsence.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRechercherNoteAvecAbsence.addActionListener(new SearchNoteWithAbsenceListener());
        panel.add(btnRechercherNoteAvecAbsence, gbc);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Liste des étudiants",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 102, 204)));

        String[] columnNames = { "Nom", "Prénom", "CNE", "Note 1", "Note 2", "Moyenne" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableEtudiants = new JTable(tableModel);
        tableEtudiants.setFont(new Font("Arial", Font.PLAIN, 12));
        tableEtudiants.setRowHeight(25);
        tableEtudiants.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableEtudiants.getTableHeader().setBackground(new Color(0, 102, 204));
        tableEtudiants.getTableHeader().setForeground(Color.WHITE);
        tableEtudiants.setSelectionBackground(new Color(184, 207, 229));

        JScrollPane scrollPane = new JScrollPane(tableEtudiants);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnAfficherTous = createStyledButton("Afficher tous (triés)");
        btnAfficherTous.addActionListener(e -> loadEtudiantsTries());
        southPanel.add(btnAfficherTous);

        btnAfficherValidants = createStyledButton("Afficher validants");
        btnAfficherValidants.addActionListener(e -> loadEtudiantsValidant());
        southPanel.add(btnAfficherValidants);

        btnAfficherMajorants = createStyledButton("Afficher majorant(s)");
        btnAfficherMajorants.addActionListener(e -> loadMajorants());
        southPanel.add(btnAfficherMajorants);

        return southPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    private void updateTable(List<Etudiant> etudiants) {
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
            if (etudiants == null || etudiants.isEmpty()) {
                showError("Aucun étudiant trouvé.");
                return;
            }
            for (Etudiant e : etudiants) {
                Object[] row = {
                        e.getNom(),
                        e.getPrenom(),
                        e.getCne() != null ? e.getCne() : "",
                        String.format("%.2f", e.getNote1()),
                        String.format("%.2f", e.getNote2()),
                        String.format("%.2f", e.getMoyenne())
                };
                tableModel.addRow(row);
            }
        });
    }

    private void loadEtudiantsTries() {
        new Thread(() -> {
            try {
                List<Etudiant> etudiants = serviceClient.getEtudiantsTries();
                updateTable(etudiants);
            } catch (Exception ex) {
                showError("Erreur: " + ex.getMessage());
            }
        }).start();
    }

    private void loadEtudiantsValidant() {
        new Thread(() -> {
            try {
                List<Etudiant> etudiants = serviceClient.getEtudiantsValidant();
                updateTable(etudiants);
            } catch (Exception ex) {
                showError("Erreur: " + ex.getMessage());
            }
        }).start();
    }

    private void loadMajorants() {
        new Thread(() -> {
            try {
                List<Etudiant> etudiants = serviceClient.getMajorants();
                updateTable(etudiants);
            } catch (Exception ex) {
                showError("Erreur: " + ex.getMessage());
            }
        }).start();
    }

    private void clearForm() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtCne.setText("");
        txtNote1.setText("");
        txtNote2.setText("");
        txtNom.requestFocus();
    }

    private void showError(String message) {
        SwingUtilities
                .invokeLater(() -> JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE));
    }

    private void showSuccess(String message) {
        SwingUtilities.invokeLater(
                () -> JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE));
    }

    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();
            String cne = txtCne.getText().trim();
            String note1Str = txtNote1.getText().trim();
            String note2Str = txtNote2.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || cne.isEmpty() || note1Str.isEmpty() || note2Str.isEmpty()) {
                showError("Tous les champs sont obligatoires.");
                return;
            }

            try {
                double note1 = Double.parseDouble(note1Str);
                double note2 = Double.parseDouble(note2Str);

                Etudiant etudiant = new Etudiant(nom, prenom, cne, note1, note2);

                new Thread(() -> {
                    try {
                        jakarta.ws.rs.core.Response response = serviceClient.addEtudiant(etudiant);
                        if (response.getStatus() == 201) {
                            showSuccess("Étudiant ajouté avec succès!");
                            SwingUtilities.invokeLater(() -> clearForm());
                            loadEtudiantsTries();
                        } else {
                            showError("Erreur lors de l'ajout: " + response.getStatus());
                        }
                    } catch (Exception ex) {
                        showError("Erreur: " + ex.getMessage());
                    }
                }).start();
            } catch (NumberFormatException ex) {
                showError("Les notes doivent être des nombres valides.");
            }
        }
    }

    private class SearchNoteWithAbsenceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cne = txtRechercheCne.getText().trim();
            if (cne.isEmpty()) {
                showError("Veuillez entrer un CNE.");
                return;
            }

            btnRechercherNoteAvecAbsence.setEnabled(false);

            new Thread(() -> {
                try {
                    NoteFinalResponse response = serviceClient.getNoteFinalAvecAbsence(cne);
                    SwingUtilities.invokeLater(() -> {
                        if (response != null) {
                            String message = String.format(
                                    "Étudiant: %s %s\n" +
                                            "CNE: %s\n\n" +
                                            "Moyenne (M): %.2f/20\n" +
                                            "Taux d'absence (T): %.1f%%\n\n" +
                                            "Note finale (N = M - T×M): %.2f/20",
                                    response.getNom(),
                                    response.getPrenom(),
                                    response.getCne(),
                                    response.getMoyenne(),
                                    response.getTauxAbsence() * 100,
                                    response.getNoteFinal());
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    message,
                                    "Note Finale avec Absence (REST)",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            showError("Étudiant non trouvé avec le CNE: " + cne);
                        }
                        btnRechercherNoteAvecAbsence.setEnabled(true);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur: " + ex.getMessage());
                        btnRechercherNoteAvecAbsence.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
