package org.example.ui;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final Client client;
    private final WebTarget absences;
    private final DefaultTableModel tableModel;

    public MainFrame() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String base = System.getenv().getOrDefault("ABSENCE_BASE_URI", "http://localhost:8081/api/etudiants");
        this.absences = client.target(base);

        setTitle("Absences Client (Swing)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[] { "ID", "Nom", "Prénom", "CNE", "Niveau", "HeuresAbsence" }, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Controls
        JPanel controls = new JPanel(new FlowLayout());
        JButton btnList = new JButton("Tous");
        JButton btnBlacklist = new JButton("Liste noire");
        JButton btnAdd = new JButton("Ajouter");
        JTextField nom = new JTextField(8);
        JTextField prenom = new JTextField(8);
        JTextField cne = new JTextField(8);
        JTextField niveau = new JTextField(6);
        JTextField heures = new JTextField(5);
        controls.add(new JLabel("Nom"));
        controls.add(nom);
        controls.add(new JLabel("Prénom"));
        controls.add(prenom);
        controls.add(new JLabel("CNE"));
        controls.add(cne);
        controls.add(new JLabel("Niveau"));
        controls.add(niveau);
        controls.add(new JLabel("Absences"));
        controls.add(heures);
        controls.add(btnAdd);
        controls.add(btnList);
        controls.add(btnBlacklist);
        add(controls, BorderLayout.NORTH);

        btnList.addActionListener(e -> load(""));
        btnBlacklist.addActionListener(e -> load("/blacklist"));
        btnAdd.addActionListener(
                e -> add(nom.getText(), prenom.getText(), cne.getText(), niveau.getText(), heures.getText()));
    }

    private void load(String path) {
        try {
            Response r = absences.path(path).request(MediaType.APPLICATION_JSON).get();
            List<Etudiant> list = r.readEntity(new GenericType<List<Etudiant>>() {
            });
            tableModel.setRowCount(0);
            for (Etudiant e : list) {
                tableModel.addRow(new Object[] { e.getId(), e.getNom(), e.getPrenom(), e.getCne(), e.getNiveau(),
                        e.getHeuresAbsence() });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
        }
    }

    private void add(String nom, String prenom, String cne, String niveau, String heuresStr) {
        try {
            Integer heures = Integer.parseInt(heuresStr);
            Etudiant e = new Etudiant(nom, prenom, cne, niveau, heures);
            Response r = absences.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(e, MediaType.APPLICATION_JSON));
            if (r.getStatus() == 201 || r.getStatus() == 200) {
                JOptionPane.showMessageDialog(this, "Ajout réussi");
            } else {
                JOptionPane.showMessageDialog(this, "Echec: " + r.getStatus());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
        }
    }

    public static class Etudiant {
        private Long id;
        private String nom;
        private String prenom;
        private String cne;
        private String niveau;
        private Integer heuresAbsence;
        private Double tauxAbsence;
        private Boolean listeNoire;

        public Etudiant() {
        }

        public Etudiant(String nom, String prenom, String cne, String niveau, Integer heuresAbsence) {
            this.nom = nom;
            this.prenom = prenom;
            this.cne = cne;
            this.niveau = niveau;
            this.heuresAbsence = heuresAbsence;
        }

        public Long getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public String getCne() {
            return cne;
        }

        public String getNiveau() {
            return niveau;
        }

        public Integer getHeuresAbsence() {
            return heuresAbsence;
        }

        public Double getTauxAbsence() {
            return tauxAbsence;
        }

        public Boolean getListeNoire() {
            return listeNoire;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setNom(String s) {
            nom = s;
        }

        public void setTauxAbsence(Double v) {
            tauxAbsence = v;
        }

        public void setListeNoire(Boolean v) {
            listeNoire = v;
        }

        public void setPrenom(String s) {
            prenom = s;
        }

        public void setCne(String s) {
            cne = s;
        }

        public void setNiveau(String s) {
            niveau = s;
        }

        public void setHeuresAbsence(Integer v) {
            heuresAbsence = v;
        }
    }
}
