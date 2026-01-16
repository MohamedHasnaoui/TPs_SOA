package org.example.client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionAbsenceUI extends JFrame {
    
    private final AbsenceServiceClient serviceClient;
    
    private JTextField txtId, txtCne, txtNom, txtPrenom, txtNiveau, txtHeuresAbsence;
    private JTable tableEtudiants, tableListeNoire;
    private DefaultTableModel modelEtudiants, modelListeNoire;
    private JSpinner spinnerSeuil;
    
    public GestionAbsenceUI() {
        serviceClient = new AbsenceServiceClient();
        
        setTitle("Gestion de l'Absentéisme des Étudiants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        appliquerLookAndFeel();
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestion des Étudiants", creerOngletGestion());
        tabbedPane.addTab("Liste Noire", creerOngletListeNoire());
        
        add(tabbedPane);

        // Charger la liste des étudiants au démarrage
        chargerListeEtudiants();
    }
    
    private void appliquerLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus Look and Feel non disponible");
        }
    }
    
    private JPanel creerOngletGestion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelHaut = new JPanel(new BorderLayout(10, 10));
        panelHaut.add(creerPanneauFormulaire(), BorderLayout.CENTER);
        panelHaut.add(creerPanneauBoutons(), BorderLayout.SOUTH);
        
        panel.add(panelHaut, BorderLayout.NORTH);
        panel.add(creerPanneauTableauEtudiants(), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel creerPanneauFormulaire() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Informations de l'étudiant", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        txtId = new JTextField(20);
        txtCne = new JTextField(20);
        txtNom = new JTextField(20);
        txtPrenom = new JTextField(20);
        txtNiveau = new JTextField(20);
        txtHeuresAbsence = new JTextField(20);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID :"), gbc);
        gbc.gridx = 1;
        panel.add(txtId, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("CNE :"), gbc);
        gbc.gridx = 1;
        panel.add(txtCne, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        panel.add(txtNom, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 3;
        panel.add(txtPrenom, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Niveau :"), gbc);
        gbc.gridx = 3;
        panel.add(txtNiveau, gbc);
        
        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel("Heures d'absence :"), gbc);
        gbc.gridx = 3;
        panel.add(txtHeuresAbsence, gbc);
        
        return panel;
    }
    
    private JPanel creerPanneauBoutons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(e -> ajouterEtudiant());
        
        JButton btnMettreAJour = new JButton("Mettre à jour");
        btnMettreAJour.addActionListener(e -> mettreAJourEtudiant());
        
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> supprimerEtudiant());
        
        JButton btnChercher = new JButton("Chercher Taux");
        btnChercher.addActionListener(e -> chercherTauxAbsence());
        
        JButton btnRafraichir = new JButton("Rafraîchir Liste");
        btnRafraichir.addActionListener(e -> chargerListeEtudiants());

        JButton btnVider = new JButton("Vider");
        btnVider.addActionListener(e -> viderFormulaire());
        
        panel.add(btnAjouter);
        panel.add(btnMettreAJour);
        panel.add(btnSupprimer);
        panel.add(btnChercher);
        panel.add(btnRafraichir);
        panel.add(btnVider);
        
        return panel;
    }
    
    private JPanel creerPanneauTableauEtudiants() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Liste des étudiants", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)));
        
        String[] colonnes = {"ID", "CNE", "Nom", "Prénom", "Niveau", "Heures d'absence"};
        modelEtudiants = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableEtudiants = new JTable(modelEtudiants);
        tableEtudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEtudiants.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                remplirFormulaireDepuisSelection();
            }
        });
        
        panel.add(new JScrollPane(tableEtudiants), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel creerOngletListeNoire() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(creerPanneauConfiguration(), BorderLayout.NORTH);
        panel.add(creerPanneauTableauListeNoire(), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel creerPanneauConfiguration() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Configuration", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)));
        
        JLabel lblSeuil = new JLabel("Seuil d'absence en % (ex: 50) :");
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(50, 0, 100, 5);
        spinnerSeuil = new JSpinner(spinnerModel);
        spinnerSeuil.setPreferredSize(new Dimension(100, 25));
        
        JButton btnGenerer = new JButton("Générer la Liste Noire");
        btnGenerer.addActionListener(e -> genererListeNoire());
        
        panel.add(lblSeuil);
        panel.add(spinnerSeuil);
        panel.add(btnGenerer);
        
        return panel;
    }
    
    private JPanel creerPanneauTableauListeNoire() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Étudiants dépassant le seuil", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)));
        
        String[] colonnes = {"ID", "CNE", "Nom", "Prénom", "Niveau", "Heures d'absence"};
        modelListeNoire = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableListeNoire = new JTable(modelListeNoire);
        tableListeNoire.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panel.add(new JScrollPane(tableListeNoire), BorderLayout.CENTER);
        return panel;
    }
    
    private void ajouterEtudiant() {
        if (!validerFormulaire()) return;
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                long id = Long.parseLong(txtId.getText().trim());
                String cne = txtCne.getText().trim();
                String nom = txtNom.getText().trim();
                String prenom = txtPrenom.getText().trim();
                String niveau = txtNiveau.getText().trim();
                int heures = Integer.parseInt(txtHeuresAbsence.getText().trim());
                
                Etudiant etudiant = serviceClient.creerEtudiant(id, cne, nom, prenom, niveau, heures);
                serviceClient.ajouterEtudiant(etudiant);
                return null;
            }
            
            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Étudiant ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    viderFormulaire();
                    chargerListeEtudiants(); // Rafraîchir la liste
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Erreur lors de l'ajout : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private void mettreAJourEtudiant() {
        if (!validerFormulaire()) return;
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                long id = Long.parseLong(txtId.getText().trim());
                String cne = txtCne.getText().trim();
                String nom = txtNom.getText().trim();
                String prenom = txtPrenom.getText().trim();
                String niveau = txtNiveau.getText().trim();
                int heures = Integer.parseInt(txtHeuresAbsence.getText().trim());
                
                Etudiant etudiant = serviceClient.creerEtudiant(id, cne, nom, prenom, niveau, heures);
                serviceClient.mettreAJourEtudiant(etudiant);
                return null;
            }
            
            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Étudiant mis à jour avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    viderFormulaire();
                    chargerListeEtudiants(); // Rafraîchir la liste
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Erreur lors de la mise à jour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private void supprimerEtudiant() {
        String idText = txtId.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez saisir l'ID de l'étudiant à supprimer.",
                "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Êtes-vous sûr de vouloir supprimer cet étudiant ?",
            "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirmation != JOptionPane.YES_OPTION) return;
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                long id = Long.parseLong(idText);
                serviceClient.supprimerEtudiant(id);
                return null;
            }
            
            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Étudiant supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    viderFormulaire();
                    chargerListeEtudiants(); // Rafraîchir la liste
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private void chercherTauxAbsence() {
        String idText = txtId.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez saisir l'ID de l'étudiant.",
                "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        new SwingWorker<Double, Void>() {
            @Override
            protected Double doInBackground() throws Exception {
                long id = Long.parseLong(idText);
                return serviceClient.obtenirTauxAbsence(id);
            }
            
            @Override
            protected void done() {
                try {
                    Double taux = get();
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        String.format("Taux d'absence de l'étudiant (ID: %s) : %.2f%%", idText, taux),
                        "Taux d'absence", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Erreur lors de la recherche : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private void genererListeNoire() {
        int seuil = (Integer) spinnerSeuil.getValue();
        
        new SwingWorker<List<Etudiant>, Void>() {
            @Override
            protected List<Etudiant> doInBackground() throws Exception {
                return serviceClient.genererListeNoire(seuil);
            }
            
            @Override
            protected void done() {
                try {
                    List<Etudiant> listeNoire = get();
                    modelListeNoire.setRowCount(0);
                    
                    if (listeNoire != null && !listeNoire.isEmpty()) {
                        for (Etudiant etudiant : listeNoire) {
                            Object[] row = {
                                etudiant.getId(),
                                etudiant.getCne(),
                                etudiant.getNom(),
                                etudiant.getPrenom(),
                                etudiant.getNiveau(),
                                etudiant.getNombreHeuresAbsence()
                            };
                            modelListeNoire.addRow(row);
                        }
                        
                        JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                            String.format("Liste noire générée : %d étudiant(s) trouvé(s).", listeNoire.size()),
                            "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                            "Aucun étudiant ne dépasse le seuil spécifié.",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(GestionAbsenceUI.this,
                        "Erreur lors de la génération de la liste noire : " + e.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private boolean validerFormulaire() {
        if (txtId.getText().trim().isEmpty() ||
            txtCne.getText().trim().isEmpty() ||
            txtNom.getText().trim().isEmpty() ||
            txtPrenom.getText().trim().isEmpty() ||
            txtNiveau.getText().trim().isEmpty() ||
            txtHeuresAbsence.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "Tous les champs doivent être remplis.",
                "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            Long.parseLong(txtId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "L'ID doit être un nombre entier valide.",
                "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int heures = Integer.parseInt(txtHeuresAbsence.getText().trim());
            if (heures < 0) {
                JOptionPane.showMessageDialog(this,
                    "Les heures d'absence doivent être un nombre positif.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Les heures d'absence doivent être un nombre entier valide.",
                "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void viderFormulaire() {
        txtId.setText("");
        txtCne.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtNiveau.setText("");
        txtHeuresAbsence.setText("");
        txtId.requestFocus();
    }
    
    private void remplirFormulaireDepuisSelection() {
        int selectedRow = tableEtudiants.getSelectedRow();
        
        if (selectedRow >= 0) {
            txtId.setText(modelEtudiants.getValueAt(selectedRow, 0).toString());
            txtCne.setText(modelEtudiants.getValueAt(selectedRow, 1).toString());
            txtNom.setText(modelEtudiants.getValueAt(selectedRow, 2).toString());
            txtPrenom.setText(modelEtudiants.getValueAt(selectedRow, 3).toString());
            txtNiveau.setText(modelEtudiants.getValueAt(selectedRow, 4).toString());
            txtHeuresAbsence.setText(modelEtudiants.getValueAt(selectedRow, 5).toString());
        }
    }

    /**
     * Charge la liste de tous les étudiants depuis le service web
     * et les affiche dans le tableau
     */
    private void chargerListeEtudiants() {
        new SwingWorker<List<Etudiant>, Void>() {
            @Override
            protected List<Etudiant> doInBackground() throws Exception {
                return serviceClient.obtenirTousLesEtudiants();
            }

            @Override
            protected void done() {
                try {
                    List<Etudiant> etudiants = get();
                    modelEtudiants.setRowCount(0); // Vider le tableau

                    if (etudiants != null && !etudiants.isEmpty()) {
                        for (Etudiant etudiant : etudiants) {
                            Object[] row = {
                                etudiant.getId(),
                                etudiant.getCne(),
                                etudiant.getNom(),
                                etudiant.getPrenom(),
                                etudiant.getNiveau(),
                                etudiant.getNombreHeuresAbsence()
                            };
                            modelEtudiants.addRow(row);
                        }
                    }
                } catch (Exception e) {
                    // Ne pas afficher d'erreur au démarrage si le service n'est pas disponible
                    System.err.println("Erreur lors du chargement de la liste des étudiants : " + e.getMessage());
                }
            }
        }.execute();
    }

    /**
     * Point d'entrée principal de l'application
     * Permet de démarrer l'application directement depuis cette classe
     */
    public static void main(String[] args) {
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

