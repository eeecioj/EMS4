import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimesheetTableModel extends AbstractTableModel {
    private String[] columnNames = {"Date", "Hours Worked"};
    private List<Object[]> data;

    public TimesheetTableModel() {
        data = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        LocalDate date = LocalDate.now().withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (date.getMonthValue() == LocalDate.now().getMonthValue()) {
            if (date.getDayOfWeek().getValue() != 7) { // Exclude Sundays
                data.add(new Object[]{date.format(formatter), 0});
            }
            date = date.plusDays(1);
        }
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
        Object[] row = data.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Object[] row = data.get(rowIndex);
        row[columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Allow editing only the "Hours Worked" column
        return columnIndex == 1;
    }
}