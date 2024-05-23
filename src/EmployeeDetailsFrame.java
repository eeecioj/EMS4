import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDetailsFrame extends JFrame {
    private String employeeNumber;
    private JPanel detailsPanel;
    private JTextField monthField;
    private JButton computeButton;

    public EmployeeDetailsFrame(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        setTitle("Employee Details");
        setLayout(new BorderLayout());

        detailsPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        loadEmployeeDetails(gbc);

        JLabel monthLabel = new JLabel("Enter Month:");
        gbc.gridx = 0;
        gbc.gridy += 1;
        detailsPanel.add(monthLabel, gbc);

        monthField = new JTextField(10);
        gbc.gridx = 1;
        detailsPanel.add(monthField, gbc);

        computeButton = new JButton("Compute");
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        detailsPanel.add(computeButton, gbc);

        computeButton.addActionListener(e -> computeSalary(monthField.getText()));

        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadEmployeeDetails(GridBagConstraints gbc) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(employeeNumber)) {
                    addLabelAndValue("Employee Number:", data[0], gbc, 0);
                    addLabelAndValue("Last Name:", data[1], gbc, 1);
                    addLabelAndValue("First Name:", data[2], gbc, 2);
                    addLabelAndValue("SSS No.:", data[3], gbc, 3);
                    addLabelAndValue("PhilHealth No.:", data[4], gbc, 4);
                    addLabelAndValue("TIN:", data[5], gbc, 5);
                    addLabelAndValue("Pagibig No.:", data[6], gbc, 6);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLabelAndValue(String labelText, String valueText, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        detailsPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        detailsPanel.add(new JLabel(valueText), gbc);
    }

    private void computeSalary(String month) {
        // Implement the salary computation logic based on the selected month
        // This is a placeholder for the actual computation logic
        JOptionPane.showMessageDialog(this, "Salary computed for " + month);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeDetailsFrame("123").setVisible(true));
    }
}