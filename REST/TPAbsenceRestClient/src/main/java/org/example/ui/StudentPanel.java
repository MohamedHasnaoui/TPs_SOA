package org.example.ui;

import jakarta.ws.rs.core.Response;
import org.example.Student;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentPanel extends JPanel {

    private final StudentServiceClient client;

    private final JTextField cneField = new JTextField(15);
    private final JTextField nomField = new JTextField(15);
    private final JTextField prenomField = new JTextField(15);
    private final JTextField niveauField = new JTextField(15);
    private final JTextField absenceHoursField = new JTextField(15);

    private final DefaultTableModel tableModel;
    private final JTable studentTable;

    public StudentPanel() {
        this.client = new StudentServiceClient();
        setLayout(new BorderLayout(10, 10));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Informations de l'étudiant"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("CNE:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        formPanel.add(prenomField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Niveau:"), gbc);
        gbc.gridx = 3;
        formPanel.add(niveauField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Heures d'absence:"), gbc);
        gbc.gridx = 3;
        formPanel.add(absenceHoursField, gbc);

        // Action Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Ajouter");
        JButton updateButton = new JButton("Mettre à jour");
        JButton deleteButton = new JButton("Supprimer");
        JButton searchButton = new JButton("Chercher");
        JButton clearButton = new JButton("Effacer");


        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        // Table Panel
        String[] columnNames = {"CNE", "Nom", "Prénom", "Niveau", "Heures d'absence"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Center Panel combining form and buttons
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add Action Listeners
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        searchButton.addActionListener(e -> searchStudent());
        clearButton.addActionListener(e -> clearForm());
        
        studentTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                int selectedRow = studentTable.getSelectedRow();
                cneField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                nomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                prenomField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                niveauField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                absenceHoursField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            }
        });

        loadStudents();
    }
    
    private void clearForm() {
        cneField.setText("");
        nomField.setText("");
        prenomField.setText("");
        niveauField.setText("");
        absenceHoursField.setText("");
        studentTable.clearSelection();
    }

    private void loadStudents() {
        new SwingWorker<List<Student>, Void>() {
            @Override
            protected List<Student> doInBackground() throws Exception {
                return client.getAllStudents();
            }

            @Override
            protected void done() {
                try {
                    List<Student> students = get();
                    tableModel.setRowCount(0); // Clear existing data
                    for (Student student : students) {
                        tableModel.addRow(new Object[]{
                                student.getCne(),
                                student.getNom(),
                                student.getPrenom(),
                                student.getNiveau(),
                                student.getAbsenceHours()
                        });
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(StudentPanel.this,
                            "Erreur lors du chargement des étudiants: " + e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void addStudent() {
        // Validation
        if (cneField.getText().trim().isEmpty() || nomField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le CNE et le Nom sont obligatoires.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double absenceHours;
        try {
            absenceHours = Double.parseDouble(absenceHoursField.getText());
            if (absenceHours < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Les heures d'absence doivent être un nombre positif.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student newStudent = new Student(
                cneField.getText(),
                nomField.getText(),
                prenomField.getText(),
                niveauField.getText(),
                absenceHours
        );

        new SwingWorker<Response, Void>() {
            @Override
            protected Response doInBackground() throws Exception {
                return client.addStudent(newStudent);
            }

            @Override
            protected void done() {
                try {
                    Response response = get();
                    if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                        JOptionPane.showMessageDialog(StudentPanel.this, "Étudiant ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        loadStudents(); // Refresh table
                        clearForm();
                    } else {
                         JOptionPane.showMessageDialog(StudentPanel.this, "Erreur: " + response.getStatusInfo().getReasonPhrase(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(StudentPanel.this, "Erreur lors de l'ajout de l'étudiant: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void updateStudent() {
        // Validation
        String cne = cneField.getText();
        if (cne.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou chercher un étudiant à mettre à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double absenceHours;
        try {
            absenceHours = Double.parseDouble(absenceHoursField.getText());
            if (absenceHours < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Les heures d'absence doivent être un nombre positif.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student updatedStudent = new Student(
                cne,
                nomField.getText(),
                prenomField.getText(),
                niveauField.getText(),
                absenceHours
        );

        new SwingWorker<Response, Void>() {
            @Override
            protected Response doInBackground() throws Exception {
                return client.updateStudent(cne, updatedStudent);
            }

            @Override
            protected void done() {
                try {
                    Response response = get();
                    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                        JOptionPane.showMessageDialog(StudentPanel.this, "Étudiant mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        loadStudents(); // Refresh table
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(StudentPanel.this, "Erreur: " + response.getStatusInfo().getReasonPhrase(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(StudentPanel.this, "Erreur lors de la mise à jour: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void deleteStudent() {
        String cne = cneField.getText();
        if (cne.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir le CNE de l'étudiant à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Êtes-vous sûr de vouloir supprimer cet étudiant?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new SwingWorker<Response, Void>() {
                @Override
                protected Response doInBackground() throws Exception {
                    return client.deleteStudent(cne);
                }

                @Override
                protected void done() {
                    try {
                        Response response = get();
                        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                            JOptionPane.showMessageDialog(StudentPanel.this, "Étudiant supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            loadStudents(); // Refresh table
                            clearForm();
                        } else {
                            JOptionPane.showMessageDialog(StudentPanel.this, "Erreur: " + response.getStatusInfo().getReasonPhrase(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                       JOptionPane.showMessageDialog(StudentPanel.this, "Erreur lors de la suppression: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }.execute();
        }
    }

    private void searchStudent() {
        String cne = cneField.getText();
        if (cne.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un CNE à chercher.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new SwingWorker<Student, Void>() {
            @Override
            protected Student doInBackground() throws Exception {
                return client.getStudent(cne);
            }

            @Override
            protected void done() {
                try {
                    Student student = get();
                    if (student != null) {
                        nomField.setText(student.getNom());
                        prenomField.setText(student.getPrenom());
                        niveauField.setText(student.getNiveau());
                        absenceHoursField.setText(String.valueOf(student.getAbsenceHours()));
                    } else {
                        JOptionPane.showMessageDialog(StudentPanel.this, "Étudiant non trouvé.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(StudentPanel.this, "Erreur lors de la recherche: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
}
