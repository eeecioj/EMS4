import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable employeeTable;
    private EmployeeTableModel tableModel;
    private TimesheetTableModel timesheetTableModel;

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        // Create and add the employee panel
        JPanel employeePanel = createEmployeeDetailsPanel();
        add(employeePanel, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createEmployeeDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;

        // Add Employee Button
        JButton addButton = new JButton("Create");
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addButton, gbc);

        // Update Employee Button
        JButton updateButton = new JButton("Update");
        gbc.gridy = 1;
        buttonPanel.add(updateButton, gbc);

        // Delete Employee Button
        JButton deleteButton = new JButton("Delete");
        gbc.gridy = 2;
        buttonPanel.add(deleteButton, gbc);

        // Timesheet Button
        JButton timesheetButton = new JButton("Timesheet");
        gbc.gridy = 3;
        buttonPanel.add(timesheetButton, gbc);

        // Compensation Button
        JButton compensationButton = new JButton("Compensation");
        gbc.gridy = 4;
        buttonPanel.add(compensationButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        panel.add(rightPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        timesheetButton.addActionListener(e -> saveTimesheet());
        compensationButton.addActionListener(e -> openCompensationFrame());

        return panel;
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

    private void saveTimesheet() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Show TimesheetFrame only if an employee is selected
            timesheetTableModel = new TimesheetTableModel(); // Ensure the model is initialized
            TimesheetFrame timesheetFrame = new TimesheetFrame(timesheetTableModel);
            timesheetFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to save timesheet.");
        }
    }

    private void addEmployee() {
        new AddEmployeeFrame(tableModel).setVisible(true);
    }

    private void openCompensationFrame() {
        JFrame compensationFrame = new JFrame("Compensation Details");
        compensationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        compensationFrame.setSize(400, 300);
        compensationFrame.setLocationRelativeTo(null);

        CompensationFrame compensationPanel = new CompensationFrame();
        compensationFrame.add(compensationPanel);
        compensationFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
