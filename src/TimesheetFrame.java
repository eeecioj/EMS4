import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class TimesheetFrame extends JFrame {
    private TimesheetTableModel timesheetTableModel;
    private JTable timesheetTable;

    public TimesheetFrame(TimesheetTableModel timesheetTableModel) {
        this.timesheetTableModel = timesheetTableModel;
        setTitle("Timesheet");

        // Create panel for the table
        JPanel mainPanel = new JPanel(new GridBagLayout());

        // Initialize and configure the timesheet table
        timesheetTable = new JTable(timesheetTableModel);
        timesheetTable.setRowHeight(30); // Set row height for better visibility
        timesheetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        timesheetTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        timesheetTable.getTableHeader().setResizingAllowed(false); // Disable column resizing

        // Set preferred width for columns
        TableColumnModel columnModel = timesheetTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200); // Date column
        columnModel.getColumn(1).setPreferredWidth(200); // Hours worked column

        JScrollPane scrollPane = new JScrollPane(timesheetTable);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(scrollPane, gbc);

        // Create panel for the Save button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save Timesheet");
        saveButton.addActionListener(e -> saveTimesheet());
        buttonPanel.add(saveButton);

        gbc.gridy = 1;
        gbc.weighty = 0;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        setSize(500, 400); // Adjusted frame size
        setLocationRelativeTo(null);
    }

    private void saveTimesheet() {
        // Logic to save timesheet
        JOptionPane.showMessageDialog(this, "Timesheet saved successfully.");
    }
}
