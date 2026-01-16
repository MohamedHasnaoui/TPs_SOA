package org.example.ui;

import org.example.Student;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BlacklistPanel extends JPanel {

    private final StudentServiceClient client;
    private final JSpinner thresholdSpinner;
    private final DefaultTableModel tableModel;

    public BlacklistPanel() {
        this.client = new StudentServiceClient();
        setLayout(new BorderLayout(10, 10));

        // Configuration Panel
        JPanel configPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        configPanel.setBorder(new TitledBorder("Configuration"));
        configPanel.add(new JLabel("Seuil d'absence (ex: 50):"));
        thresholdSpinner = new JSpinner(new SpinnerNumberModel(50.0, 0.0, 1000.0, 1.0));
        configPanel.add(thresholdSpinner);

        JButton generateButton = new JButton("Générer la Liste Noire");
        configPanel.add(generateButton);

        // Results Panel
        String[] columnNames = {"CNE", "Nom", "Prénom", "Heures d'absence"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable blacklistTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(blacklistTable);
        scrollPane.setBorder(new TitledBorder("Étudiants dépassant le seuil"));

        add(configPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listener
        generateButton.addActionListener(e -> generateBlacklist());
    }

    private void generateBlacklist() {
        double threshold = (Double) thresholdSpinner.getValue();

        new SwingWorker<List<Student>, Void>() {
            @Override
            protected List<Student> doInBackground() throws Exception {
                return client.getBlacklist(threshold);
            }

            @Override
            protected void done() {
                try {
                    List<Student> blacklist = get();
                    tableModel.setRowCount(0); // Clear existing data
                    for (Student student : blacklist) {
                        tableModel.addRow(new Object[]{
                                student.getCne(),
                                student.getNom(),
                                student.getPrenom(),
                                student.getAbsenceHours()
                        });
                    }
                     if (blacklist.isEmpty()) {
                        JOptionPane.showMessageDialog(BlacklistPanel.this,
                                "Aucun étudiant ne dépasse le seuil spécifié.",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(BlacklistPanel.this,
                            "Erreur lors de la génération de la liste noire: " + e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
}
