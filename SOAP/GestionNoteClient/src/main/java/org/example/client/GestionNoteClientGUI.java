package org.example.client;

import org.example.client.ws.Etudiant;
import org.example.client.ws.GestionNoteService;
import org.example.client.ws.GestionNoteServiceImplService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interface graphique Swing professionnelle pour la gestion des notes
 * d'étudiants.
 * Consomme un service web JAX-WS pour effectuer les opérations CRUD.
 */
public class GestionNoteClientGUI extends JFrame {
    // Composants de l'interface
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtNote1;
    private JTextField txtNote2;
    private JTextField txtCne; // Nouveau champ CNE
    private JTable tableEtudiants;
    private DefaultTableModel tableModel;
    private JButton btnAjouter;
    private JButton btnAfficherTous;
    private JButton btnAfficherValidants;
    private JButton btnAfficherMajorants;
    private JButton btnRechercherNote;
    private JTextField txtRechercheNom;
    private JTextField txtRechercheCne; // Nouveau champ pour recherche par CNE
    private JButton btnRechercherNoteAvecAbsence; // Nouveau bouton
    // Service web proxy
    private GestionNoteService serviceProxy;

    /**
     * Constructeur de l'interface graphique.
     */
    public GestionNoteClientGUI() {
        // Initialiser le service web
        initializeWebService();
        // Configurer le Look and Feel Nimbus
        setLookAndFeel();
        // Configurer la fenêtre principale
        setTitle("Gestion des Étudiants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLayout(new BorderLayout(10, 10));
        // Créer et ajouter les panneaux
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);
        // Centrer la fenêtre sur l'écran
        setLocationRelativeTo(null);
        // Afficher la fenêtre
        setVisible(true);
    }

    /**
     * Initialise la connexion au service web.
     */
    private void initializeWebService() {
        try {
            GestionNoteServiceImplService service = new GestionNoteServiceImplService();
            serviceProxy = service.getGestionNoteServiceImplPort();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la connexion au service web:\n" + e.getMessage(),
                    "Erreur de connexion",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Configure le Look and Feel Nimbus pour un design moderne.
     */
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

    /**
     * Crée le panneau Nord contenant les formulaires d'ajout et de recherche.
     */
    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        northPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Panneau d'ajout d'étudiant
        northPanel.add(createAddStudentPanel());
        // Panneau de recherche de note
        northPanel.add(createSearchPanel());
        // Panneau de recherche de note avec prise en compte de l'absence
        northPanel.add(createSearchWithAbsencePanel());
        return northPanel;
    }

    /**
     * Crée le panneau d'ajout d'étudiant.
     */
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
        txtNom = new JTextField(20);
        panel.add(txtNom, gbc);
        // Prénom
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPrenom = new JTextField(20);
        panel.add(txtPrenom, gbc);
        // CNE
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("CNE:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCne = new JTextField(20);
        panel.add(txtCne, gbc);
        // Note 1
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Note 1:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNote1 = new JTextField(20);
        panel.add(txtNote1, gbc);
        // Note 2
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Note 2:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNote2 = new JTextField(20);
        panel.add(txtNote2, gbc);
        // Bouton Ajouter
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAjouter.setFocusPainted(false);
        btnAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAjouter.addActionListener(new AddStudentListener());
        panel.add(btnAjouter, gbc);
        return panel;
    }

    /**
     * Crée le panneau de recherche de note.
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Rechercher la note moyenne",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 102, 204)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Nom de l'étudiant
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtRechercheNom = new JTextField(20);
        panel.add(txtRechercheNom, gbc);
        // Bouton Rechercher
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        btnRechercherNote = new JButton("Rechercher Note");
        btnRechercherNote.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRechercherNote.setFocusPainted(false);
        btnRechercherNote.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRechercherNote.addActionListener(new SearchNoteListener());
        panel.add(btnRechercherNote, gbc);
        return panel;
    }

    /**
     * Crée le panneau de recherche de note avec prise en compte de l'absence.
     * Utilise le CNE pour identifier l'étudiant et appliquer la formule N = M - T*M
     */
    private JPanel createSearchWithAbsencePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Note finale (avec absence)",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(204, 102, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // CNE de l'étudiant
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("CNE:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtRechercheCne = new JTextField(20);
        panel.add(txtRechercheCne, gbc);
        // Info formule
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JLabel lblFormule = new JLabel("Formule: N = M - T×M");
        lblFormule.setFont(new Font("Arial", Font.ITALIC, 10));
        lblFormule.setForeground(Color.GRAY);
        panel.add(lblFormule, gbc);
        // Bouton Rechercher
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        btnRechercherNoteAvecAbsence = new JButton("Calculer Note Finale");
        btnRechercherNoteAvecAbsence.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRechercherNoteAvecAbsence.setFocusPainted(false);
        btnRechercherNoteAvecAbsence.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRechercherNoteAvecAbsence.addActionListener(new SearchNoteWithAbsenceListener());
        panel.add(btnRechercherNoteAvecAbsence, gbc);
        return panel;
    }

    /**
     * Crée le panneau central contenant la table des étudiants.
     */
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Liste des étudiants",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 102, 204)));
        // Créer le modèle de table
        String[] columnNames = { "Nom", "Prénom", "CNE", "Note 1", "Note 2", "Moyenne" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table non éditable
            }
        };
        // Créer la table
        tableEtudiants = new JTable(tableModel);
        tableEtudiants.setFont(new Font("Arial", Font.PLAIN, 12));
        tableEtudiants.setRowHeight(25);
        tableEtudiants.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableEtudiants.getTableHeader().setBackground(new Color(0, 102, 204));
        tableEtudiants.getTableHeader().setForeground(Color.WHITE);
        tableEtudiants.setSelectionBackground(new Color(184, 207, 229));
        tableEtudiants.setGridColor(new Color(200, 200, 200));
        // Ajouter la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableEtudiants);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        return centerPanel;
    }

    /**
     * Crée le panneau Sud contenant les boutons d'action.
     */
    private JPanel createSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Bouton Afficher tous les étudiants (triés)
        btnAfficherTous = createStyledButton("Afficher tous les étudiants (triés)");
        btnAfficherTous.addActionListener(new ShowAllStudentsListener());
        southPanel.add(btnAfficherTous);
        // Bouton Afficher les étudiants ayant validé
        btnAfficherValidants = createStyledButton("Afficher les étudiants ayant validé");
        btnAfficherValidants.addActionListener(new ShowValidatingStudentsListener());
        southPanel.add(btnAfficherValidants);
        // Bouton Afficher le(s) majorant(s)
        btnAfficherMajorants = createStyledButton("Afficher le(s) majorant(s)");
        btnAfficherMajorants.addActionListener(new ShowTopStudentsListener());
        southPanel.add(btnAfficherMajorants);
        return southPanel;
    }

    /**
     * Crée un bouton stylisé simple.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(280, 40));
        return button;
    }

    /**
     * Valide les entrées du formulaire d'ajout d'étudiant.
     */
    private boolean validateStudentInput() {
        // Vérifier que les champs ne sont pas vides
        if (txtNom.getText().trim().isEmpty()) {
            showError("Le nom est obligatoire.");
            txtNom.requestFocus();
            return false;
        }
        if (txtPrenom.getText().trim().isEmpty()) {
            showError("Le prénom est obligatoire.");
            txtPrenom.requestFocus();
            return false;
        }
        // Valider Note 1
        try {
            double note1 = Double.parseDouble(txtNote1.getText().trim());
            if (note1 < 0 || note1 > 20) {
                showError("La Note 1 doit être entre 0 et 20.");
                txtNote1.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("La Note 1 doit être un nombre valide.");
            txtNote1.requestFocus();
            return false;
        }
        // Valider Note 2
        try {
            double note2 = Double.parseDouble(txtNote2.getText().trim());
            if (note2 < 0 || note2 > 20) {
                showError("La Note 2 doit être entre 0 et 20.");
                txtNote2.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("La Note 2 doit être un nombre valide.");
            txtNote2.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Vide les champs du formulaire d'ajout.
     */
    private void clearForm() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtCne.setText("");
        txtNote1.setText("");
        txtNote2.setText("");
        txtNom.requestFocus();
    }

    /**
     * Calcule la moyenne de deux notes.
     */
    private double calculateAverage(double note1, double note2) {
        return (note1 + note2) / 2.0;
    }

    /**
     * Affiche un message d'erreur.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Erreur de validation",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Affiche un message de succès.
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Met à jour la table avec une liste d'étudiants.
     */
    private void updateTable(List<Etudiant> etudiants) {
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0); // Vider la table
            if (etudiants == null || etudiants.isEmpty()) {
                showError("Aucun étudiant trouvé.");
                return;
            }
            for (Etudiant etudiant : etudiants) {
                double moyenne = calculateAverage(etudiant.getNote1(), etudiant.getNote2());
                Object[] row = {
                        etudiant.getNom(),
                        etudiant.getPrenom(),
                        etudiant.getCne() != null ? etudiant.getCne() : "",
                        String.format("%.2f", etudiant.getNote1()),
                        String.format("%.2f", etudiant.getNote2()),
                        String.format("%.2f", moyenne)
                };
                tableModel.addRow(row);
            }
        });
    }

    /**
     * ActionListener pour le bouton d'ajout d'étudiant.
     */
    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!validateStudentInput()) {
                return;
            }
            // Désactiver le bouton pendant le traitement
            btnAjouter.setEnabled(false);
            // Exécuter l'appel au service web dans un thread séparé
            new Thread(() -> {
                try {
                    Etudiant etudiant = new Etudiant();
                    etudiant.setNom(txtNom.getText().trim());
                    etudiant.setPrenom(txtPrenom.getText().trim());
                    etudiant.setCne(txtCne.getText().trim());
                    etudiant.setNote1(Double.parseDouble(txtNote1.getText().trim()));
                    etudiant.setNote2(Double.parseDouble(txtNote2.getText().trim()));
                    boolean result = serviceProxy.ajouterEtudiant(etudiant);
                    SwingUtilities.invokeLater(() -> {
                        if (result) {
                            showSuccess("Étudiant ajouté avec succès !\n" +
                                    "Nom: " + etudiant.getNom() + "\n" +
                                    "Prénom: " + etudiant.getPrenom() + "\n" +
                                    "CNE: " + etudiant.getCne() + "\n" +
                                    "Moyenne: " + String.format("%.2f",
                                            calculateAverage(etudiant.getNote1(), etudiant.getNote2())));
                            clearForm();
                        } else {
                            showError("Impossible d'ajouter l'étudiant. Peut-être existe-t-il déjà ?");
                        }
                        btnAjouter.setEnabled(true);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors de l'ajout de l'étudiant:\n" + ex.getMessage());
                        btnAjouter.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * ActionListener pour le bouton de recherche de note.
     */
    private class SearchNoteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = txtRechercheNom.getText().trim();
            if (nom.isEmpty()) {
                showError("Veuillez entrer un nom.");
                txtRechercheNom.requestFocus();
                return;
            }
            // Désactiver le bouton pendant le traitement
            btnRechercherNote.setEnabled(false);
            // Exécuter l'appel au service web dans un thread séparé
            new Thread(() -> {
                try {
                    double note = serviceProxy.getNote(nom);
                    SwingUtilities.invokeLater(() -> {
                        if (note >= 0) {
                            JOptionPane.showMessageDialog(GestionNoteClientGUI.this,
                                    "Note moyenne de " + nom + ": " + String.format("%.2f", note),
                                    "Résultat de la recherche",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            showError("Étudiant non trouvé.");
                        }
                        btnRechercherNote.setEnabled(true);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors de la recherche:\n" + ex.getMessage());
                        btnRechercherNote.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * ActionListener pour le bouton de recherche de note avec prise en compte de
     * l'absence.
     * Appelle la méthode getNoteFinalAvecAbsence du service qui communique avec le
     * service d'absence.
     */
    private class SearchNoteWithAbsenceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cne = txtRechercheCne.getText().trim();
            if (cne.isEmpty()) {
                showError("Veuillez entrer un CNE.");
                txtRechercheCne.requestFocus();
                return;
            }
            // Désactiver le bouton pendant le traitement
            btnRechercherNoteAvecAbsence.setEnabled(false);
            // Exécuter l'appel au service web dans un thread séparé
            new Thread(() -> {
                try {
                    double noteFinal = serviceProxy.getNoteFinalAvecAbsence(cne);
                    SwingUtilities.invokeLater(() -> {
                        if (noteFinal >= 0) {
                            JOptionPane.showMessageDialog(GestionNoteClientGUI.this,
                                    "Note finale (avec pénalité d'absence) pour CNE " + cne + ":\n\n" +
                                            "Note finale: " + String.format("%.2f", noteFinal) + "/20\n\n" +
                                            "Formule appliquée: N = M - T×M\n" +
                                            "(M = moyenne, T = taux d'absence)",
                                    "Résultat - Note avec Absence",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            showError("Étudiant avec CNE '" + cne + "' non trouvé.");
                        }
                        btnRechercherNoteAvecAbsence.setEnabled(true);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors du calcul de la note finale:\n" + ex.getMessage());
                        btnRechercherNoteAvecAbsence.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * ActionListener pour afficher tous les étudiants triés.
     */
    private class ShowAllStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnAfficherTous.setEnabled(false);
            new Thread(() -> {
                try {
                    List<Etudiant> etudiants = serviceProxy.getEtudiantsTries();
                    updateTable(etudiants);
                    SwingUtilities.invokeLater(() -> btnAfficherTous.setEnabled(true));
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors de la récupération des étudiants:\n" + ex.getMessage());
                        btnAfficherTous.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * ActionListener pour afficher les étudiants ayant validé.
     */
    private class ShowValidatingStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnAfficherValidants.setEnabled(false);
            new Thread(() -> {
                try {
                    List<Etudiant> etudiants = serviceProxy.getEtudiantsValidant();
                    updateTable(etudiants);
                    SwingUtilities.invokeLater(() -> btnAfficherValidants.setEnabled(true));
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors de la récupération des étudiants validants:\n" + ex.getMessage());
                        btnAfficherValidants.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * ActionListener pour afficher le(s) majorant(s).
     */
    private class ShowTopStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnAfficherMajorants.setEnabled(false);
            new Thread(() -> {
                try {
                    List<Etudiant> etudiants = serviceProxy.getMajorant();
                    updateTable(etudiants);
                    SwingUtilities.invokeLater(() -> btnAfficherMajorants.setEnabled(true));
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        showError("Erreur lors de la récupération du/des majorant(s):\n" + ex.getMessage());
                        btnAfficherMajorants.setEnabled(true);
                    });
                }
            }).start();
        }
    }

    /**
     * Point d'entrée pour tester l'interface (optionnel).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestionNoteClientGUI::new);
    }
}
