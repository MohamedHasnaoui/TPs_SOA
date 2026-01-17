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
    private final WebTarget notes;
    private final DefaultTableModel tableModel;

    public MainFrame() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String base = System.getenv().getOrDefault("NOTES_BASE_URI", "http://localhost:8082/api/notes");
        this.notes = client.target(base);

        setTitle("Notes Client (Swing)");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(
                new String[] { "ID", "Nom", "Prenom", "CNE", "Niveau", "NoteCC", "NoteTP", "NoteExam", "Moyenne" }, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Controls
        JPanel controls = new JPanel(new FlowLayout());
        JButton btnList = new JButton("Tries");
        JButton btnValidants = new JButton("Validants");
        JButton btnMajorants = new JButton("Majorants");
        JButton btnAdd = new JButton("Ajouter");
        JTextField nom = new JTextField(8);
        JTextField prenom = new JTextField(8);
        JTextField cne = new JTextField(8);
        JTextField niveau = new JTextField(4);
        JTextField noteCC = new JTextField(4);
        JTextField noteTP = new JTextField(4);
        JTextField noteExam = new JTextField(4);
        controls.add(new JLabel("Nom"));
        controls.add(nom);
        controls.add(new JLabel("Prenom"));
        controls.add(prenom);
        controls.add(new JLabel("CNE"));
        controls.add(cne);
        controls.add(new JLabel("Niveau"));
        controls.add(niveau);
        controls.add(new JLabel("CC"));
        controls.add(noteCC);
        controls.add(new JLabel("TP"));
        controls.add(noteTP);
        controls.add(new JLabel("Exam"));
        controls.add(noteExam);
        controls.add(btnAdd);
        controls.add(btnList);
        controls.add(btnValidants);
        controls.add(btnMajorants);
        add(controls, BorderLayout.NORTH);

        btnList.addActionListener(e -> load("/tries"));
        btnValidants.addActionListener(e -> load("/validant"));
        btnMajorants.addActionListener(e -> load("/majorants"));
        btnAdd.addActionListener(e -> add(nom.getText(), prenom.getText(), cne.getText(), niveau.getText(),
                noteCC.getText(), noteTP.getText(), noteExam.getText()));
    }

    private void load(String path) {
        try {
            Response r = notes.path(path).request(MediaType.APPLICATION_JSON).get();
            List<Etudiant> list = r.readEntity(new GenericType<List<Etudiant>>() {
            });
            tableModel.setRowCount(0);
            for (Etudiant e : list) {
                tableModel.addRow(new Object[] { e.getId(), e.getNom(), e.getPrenom(), e.getCne(), e.getNiveau(),
                        e.getNoteCC(), e.getNoteTP(), e.getNoteExam(), e.getMoyenne() });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
        }
    }

    private void add(String nom, String prenom, String cne, String niveau, String ccStr, String tpStr, String examStr) {
        try {
            Double cc = Double.parseDouble(ccStr);
            Double tp = Double.parseDouble(tpStr);
            Double exam = Double.parseDouble(examStr);
            Etudiant e = new Etudiant(nom, prenom, cne, niveau, cc, tp, exam);
            Response r = notes.path("/etudiant").request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(e, MediaType.APPLICATION_JSON));
            if (r.getStatus() == 200) {
                JOptionPane.showMessageDialog(this, "Ajout reussi");
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
        private Double noteCC;
        private Double noteTP;
        private Double noteExam;
        private Double moyenne;

        public Etudiant() {
        }

        public Etudiant(String nom, String prenom, String cne, String niveau, Double noteCC, Double noteTP,
                Double noteExam) {
            this.nom = nom;
            this.prenom = prenom;
            this.cne = cne;
            this.niveau = niveau;
            this.noteCC = noteCC;
            this.noteTP = noteTP;
            this.noteExam = noteExam;
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

        public Double getNoteCC() {
            return noteCC;
        }

        public Double getNoteTP() {
            return noteTP;
        }

        public Double getNoteExam() {
            return noteExam;
        }

        public Double getMoyenne() {
            return moyenne;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setNom(String s) {
            nom = s;
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

        public void setNoteCC(Double v) {
            noteCC = v;
        }

        public void setNoteTP(Double v) {
            noteTP = v;
        }

        public void setNoteExam(Double v) {
            noteExam = v;
        }

        public void setMoyenne(Double v) {
            moyenne = v;
        }
    }
}
