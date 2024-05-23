import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private String[] columnNames = {"Employee Number", "Last Name", "First Name", "SSS No.", "PhilHealth No.", "TIN", "Pagibig No."};
    private List<String[]> data;

    public EmployeeTableModel() {
        data = new ArrayList<>();
        // Load data from CSV file
        loadDataFromCSV("employees.csv");
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = data.get(rowIndex);
        return row[columnIndex];
    }

    // Make all cells uneditable
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    // Method to update employee details
    public void updateEmployeeDetails(String employeeNumber, String[] updatedDetails) {
        for (String[] row : data) {
            if (row[0].equals(employeeNumber)) {
                // Update the details
                for (int i = 0; i < updatedDetails.length; i++) {
                    row[i + 1] = updatedDetails[i]; // Skip the first element (employee number)
                }
                // Notify listeners that the data has changed
                fireTableDataChanged();
                return; // Exit loop once the employee is found and updated
            }
        }
    }

    // Method to load data from CSV file
    private void loadDataFromCSV(String csvFile) {
        String line;
        String csvSeparator = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(csvSeparator);
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an employee
    public void deleteEmployee(String employeeNumber) {
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row[0].equals(employeeNumber)) {
                data.remove(i);
                fireTableDataChanged();
                return;
            }
        }
    }

    // Method to add a row to the table
    public void addRow(String[] rowData) {
        data.add(rowData);
        fireTableDataChanged();
    }
}
