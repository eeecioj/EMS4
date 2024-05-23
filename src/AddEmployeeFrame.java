import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddEmployeeFrame extends JFrame {
    private JTextField employeeNumberField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField sssField;
    private JTextField philhealthField;
    private JTextField tinField;
    private JTextField pagIbigField;
    private JButton saveButton;
    private EmployeeTableModel tableModel;

    public AddEmployeeFrame(EmployeeTableModel tableModel) {
        this.tableModel = tableModel;
        setTitle("Add Employee");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        pack();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel sssLabel = new JLabel("SSS No.:");
        JLabel philhealthLabel = new JLabel("PhilHealth No.:");
        JLabel tinLabel = new JLabel("TIN:");
        JLabel pagIbigLabel = new JLabel("Pag-IBIG No.:");

        employeeNumberField = new JTextField(15);
        lastNameField = new JTextField(15);
        firstNameField = new JTextField(15);
        sssField = new JTextField(15);
        philhealthField = new JTextField(15);
        tinField = new JTextField(15);
        pagIbigField = new JTextField(15);

        // Set label alignment to center
        employeeNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sssLabel.setHorizontalAlignment(SwingConstants.CENTER);
        philhealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagIbigLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(employeeNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(employeeNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(sssLabel, gbc);
        gbc.gridx = 1;
        panel.add(sssField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(philhealthLabel, gbc);
        gbc.gridx = 1;
        panel.add(philhealthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(tinLabel, gbc);
        gbc.gridx = 1;
        panel.add(tinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(pagIbigLabel, gbc);
        gbc.gridx = 1;
        panel.add(pagIbigField, gbc);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveEmployee());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveEmployee() {
        // Get data from text fields
        String employeeNumber = employeeNumberField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String sss = sssField.getText().trim();
        String philhealth = philhealthField.getText().trim();
        String tin = tinField.getText().trim();
        String pagIbig = pagIbigField.getText().trim();

        // Create a string representing the employee record
        String employeeRecord = String.join(",", employeeNumber, lastName, firstName, sss, philhealth, tin, pagIbig);

        // Write the employee record to the CSV file
        try (FileWriter writer = new FileWriter("employees.csv", true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(employeeRecord);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        // Add data to the table model
        tableModel.addRow(new String[]{employeeNumber, lastName, firstName, sss, philhealth, tin, pagIbig});

        // Refresh the table in the main frame
        tableModel.fireTableDataChanged();

        // Close the frame
        dispose();
    }
}
