import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UpdateEmployeeFrame extends JFrame {
    private String employeeNumber;
    private EmployeeTableModel tableModel;
    private JTextField lastNameField, firstNameField, sssNoField, philHealthNoField, tinField, pagibigNoField;
    private JButton updateButton;

    public UpdateEmployeeFrame(String employeeNumber, EmployeeTableModel tableModel) {
        this.employeeNumber = employeeNumber;
        this.tableModel = tableModel;
        setTitle("Update Employee Details");
        setLayout(null);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 20, 80, 25);
        add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(100, 20, 165, 25);
        add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 50, 80, 25);
        add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(100, 50, 165, 25);
        add(firstNameField);

        JLabel sssNoLabel = new JLabel("SSS No.:");
        sssNoLabel.setBounds(20, 80, 80, 25);
        add(sssNoLabel);

        sssNoField = new JTextField();
        sssNoField.setBounds(100, 80, 165, 25);
        add(sssNoField);

        JLabel philHealthNoLabel = new JLabel("PhilHealth No.:");
        philHealthNoLabel.setBounds(20, 110, 100, 25);
        add(philHealthNoLabel);

        philHealthNoField = new JTextField();
        philHealthNoField.setBounds(120, 110, 145, 25);
        add(philHealthNoField);

        JLabel tinLabel = new JLabel("TIN:");
        tinLabel.setBounds(20, 140, 80, 25);
        add(tinLabel);

        tinField = new JTextField();
        tinField.setBounds(100, 140, 165, 25);
        add(tinField);

        JLabel pagibigNoLabel = new JLabel("Pagibig No.:");
        pagibigNoLabel.setBounds(20, 170, 80, 25);
        add(pagibigNoLabel);

        pagibigNoField = new JTextField();
        pagibigNoField.setBounds(100, 170, 165, 25);
        add(pagibigNoField);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 210, 100, 25);
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployeeDetails();
            }
        });

        // Call the readCSVAndUpdateFields method to populate fields
        readCSVAndUpdateFields("employees.csv", employeeNumber);

        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Method to read CSV data and update fields
    private void readCSVAndUpdateFields(String csvFile, String employeeNumber) {
        String line = "";
        String csvSeparator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read data lines
            while ((line = br.readLine()) != null) {
                // Split the line by the CSV separator
                String[] data = line.split(csvSeparator);

                // Check if the employee number matches
                if (data.length > 0 && data[0].equals(employeeNumber)) {
                    // Populate text fields with data from CSV
                    lastNameField.setText(data.length > 1 ? data[1] : "");
                    firstNameField.setText(data.length > 2 ? data[2] : "");
                    sssNoField.setText(data.length > 3 ? data[3] : "");
                    philHealthNoField.setText(data.length > 4 ? data[4] : "");
                    tinField.setText(data.length > 5 ? data[5] : "");
                    pagibigNoField.setText(data.length > 6 ? data[6] : "");
                    break; // Stop reading once employee data is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update employee details
    private void updateEmployeeDetails() {
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String sssNo = sssNoField.getText();
        String philHealthNo = philHealthNoField.getText();
        String tin = tinField.getText();
        String pagibigNo = pagibigNoField.getText();

        // Create an array with updated details
        String[] updatedDetails = {lastName, firstName, sssNo, philHealthNo, tin, pagibigNo};

        // Update the details in the table model
        tableModel.updateEmployeeDetails(employeeNumber, updatedDetails);

        // Close the frame
        dispose();
    }
    
    public void refreshEmployeeTable() {
    if (tableModel != null) {
        tableModel.fireTableDataChanged();
    }
}

}

