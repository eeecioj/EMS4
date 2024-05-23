import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable employeeTable;
    private EmployeeTableModel tableModel;
    private TimesheetTableModel timesheetTableModel;

    public MainFrame() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();

        // Create and add the employee panel
        JPanel employeePanel = new JPanel();
        employeePanel.add(createEmployeeDetailsPanel());
        tabbedPane.addTab("Employee", employeePanel);

        // Create and add the timesheet panel
        JPanel timesheetPanel = new JPanel();
        timesheetPanel.add(createTimesheetPanel());
        tabbedPane.addTab("Timesheet", timesheetPanel);

        // Create and add the compensation panel
        JPanel compensationPanel = new JPanel();
        compensationPanel.add(createCompensationPanel());
        tabbedPane.addTab("Compensation", compensationPanel);

        // Add the tabbed pane to the main frame
        add(tabbedPane, BorderLayout.CENTER);

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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add Employee Button
        JButton addButton = new JButton("Add Employee");
        gbc.gridx = 0;
        buttonPanel.add(addButton, gbc);

        // Update Employee Button
        JButton updateButton = new JButton("Update Employee");
        gbc.gridx = 1;
        buttonPanel.add(updateButton, gbc);

        // Delete Employee Button
        JButton deleteButton = new JButton("Delete Employee");
        gbc.gridx = 2;
        buttonPanel.add(deleteButton, gbc);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());

        return panel;
    }

    private void addEmployee() {
        AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame(tableModel);
        addEmployeeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                // When AddEmployeeFrame is closed, update the table model and refresh the table
                tableModel.loadDataFromCSV(); // Reload data from CSV file
                employeeTable.repaint(); // Repaint the table to reflect the changes
            }
        });
        addEmployeeFrame.setVisible(true);
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

    private JPanel createTimesheetPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        timesheetTableModel = new TimesheetTableModel();
        JTable timesheetTable = new JTable(timesheetTableModel);
        JScrollPane scrollPane = new JScrollPane(timesheetTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton saveButton = new JButton("Save Timesheet");
        gbc.gridx = 0;
        buttonPanel.add(saveButton, gbc);

        saveButton.addActionListener(e -> saveTimesheet());

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCompensationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        JLabel fullNameLabel = new JLabel("Full Name:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");

        JTextField employeeNumberField = new JTextField(15);
        JTextField fullNameField = new JTextField(15);
        JTextField positionField = new JTextField(15);
        JTextField hourlyRateField = new JTextField(15);

        employeeNumberField.setEditable(false);
        fullNameField.setEditable(false);
        positionField.setEditable(false);
        hourlyRateField.setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(employeeNumberLabel, gbc);

        gbc.gridx = 1;
        panel.add(employeeNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(fullNameLabel, gbc);

        gbc.gridx = 1;
        panel.add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(positionLabel, gbc);

        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(hourlyRateLabel, gbc);

        gbc.gridx = 1;
        panel.add(hourlyRateField, gbc);

        return panel;
    }

    private void saveTimesheet() {
        // Logic to save timesheet
        JOptionPane.showMessageDialog(this, "Timesheet saved successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
