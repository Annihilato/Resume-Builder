// Placeholder for ResumeGui.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ResumeGUI extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextArea summaryArea;
    private JTextArea experienceArea;
    private JTextArea educationArea;
    private JTextArea skillsArea;
    private JTextArea previewArea;

    public ResumeGUI() {
        super("Resume Builder");

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(6, 6, 6, 6);

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        summaryArea = new JTextArea(4, 30);
        experienceArea = new JTextArea(6, 30);
        educationArea = new JTextArea(4, 30);
        skillsArea = new JTextArea(3, 30);

        previewArea = new JTextArea();
        previewArea.setEditable(false);
        previewArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane previewScroll = new JScrollPane(previewArea);

        int y = 0;

        // Row: Name
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(nameField, gbc);
        y++;

        // Row: Email
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(emailField, gbc);
        y++;

        // Row: Phone
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(phoneField, gbc);
        y++;

        // Row: Summary
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Professional Summary:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(new JScrollPane(summaryArea), gbc);
        y++;

        // Row: Experience
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Experience (bullet points or paragraphs):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(new JScrollPane(experienceArea), gbc);
        y++;

        // Row: Education
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Education:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(new JScrollPane(educationArea), gbc);
        y++;

        // Row: Skills
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0;
        inputPanel.add(new JLabel("Skills (comma separated):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputPanel.add(new JScrollPane(skillsArea), gbc);
        y++;

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton previewButton = new JButton("Preview");
        JButton exportButton = new JButton("Export PDF");
        JButton clearButton = new JButton("Clear");

        previewButton.addActionListener(this::onPreview);
        exportButton.addActionListener(this::onExport);
        clearButton.addActionListener(e -> clearAllFields());

        buttonPanel.add(previewButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(clearButton);

        // Layout main frame: left inputs, right preview
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JPanel left = new JPanel(new BorderLayout());
        left.add(new JScrollPane(inputPanel), BorderLayout.CENTER);
        left.add(buttonPanel, BorderLayout.SOUTH);

        split.setLeftComponent(left);
        split.setRightComponent(previewScroll);
        split.setDividerLocation(480);

        getContentPane().add(split, BorderLayout.CENTER);
    }

    private void onPreview(ActionEvent e) {
        Resume r = collectResumeFromInputs();
        previewArea.setText(r.toString());
    }

    private void onExport(ActionEvent e) {
        Resume r = collectResumeFromInputs();
        previewArea.setText(r.toString()); // update preview first

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Resume as PDF");
        chooser.setSelectedFile(new File("resume.pdf"));
        int userChoice = chooser.showSaveDialog(this);
        if (userChoice != JFileChooser.APPROVE_OPTION) return;

        File picked = chooser.getSelectedFile();
        // ensure .pdf extension
        String path = picked.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".pdf")) {
            picked = new File(path + ".pdf");
        }

        try {
            Pdfexporter.export(resumeToPlainText(r), picked);
            JOptionPane.showMessageDialog(this, "PDF saved to: " + picked.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to create PDF: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Resume collectResumeFromInputs() {
        Resume r = new Resume();
        r.setFullName(nameField.getText().trim());
        r.setEmail(emailField.getText().trim());
        r.setPhone(phoneField.getText().trim());
        r.setSummary(summaryArea.getText().trim());
        r.setExperience(experienceArea.getText().trim());
        r.setEducation(educationArea.getText().trim());
        r.setSkills(skillsArea.getText().trim());
        return r;
    }

    // Prepare plain text structured content for PDF output
    private String resumeToPlainText(Resume r) {
        return r.toString();
    }

    private void clearAllFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        summaryArea.setText("");
        experienceArea.setText("");
        educationArea.setText("");
        skillsArea.setText("");
        previewArea.setText("");
    }
}
