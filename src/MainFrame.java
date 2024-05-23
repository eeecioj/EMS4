import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable employeeTable;
    private EmployeeTableModel tableModel;

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin around buttons

        JButton viewButton = new JButton("View Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");

        gbc.gridx = 0;
        buttonPanel.add(viewButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(updateButton, gbc);

        gbc.gridx = 2;
        buttonPanel.add(deleteButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);

        viewButton.addActionListener(e -> viewEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());

        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new EmployeeDetailsFrame(employeeNumber).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to view.");
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new UpdateEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
            new DeleteEmployeeFrame(employeeNumber, tableModel).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}