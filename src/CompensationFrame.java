import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompensationFrame extends JPanel {
    private JLabel employeeNumberLabel;
    private JLabel fullNameLabel;
    private JLabel positionLabel;
    private JLabel hourlyRateLabel;

    public CompensationFrame() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        employeeNumberLabel = new JLabel();
        fullNameLabel = new JLabel();
        positionLabel = new JLabel();
        hourlyRateLabel = new JLabel();

        infoPanel.add(new JLabel("Employee Number:"));
        infoPanel.add(employeeNumberLabel);

        infoPanel.add(new JLabel("Full Name:"));
        infoPanel.add(fullNameLabel);

        infoPanel.add(new JLabel("Position:"));
        infoPanel.add(positionLabel);

        infoPanel.add(new JLabel("Hourly Rate:"));
        infoPanel.add(hourlyRateLabel);

        add(infoPanel, BorderLayout.CENTER);

        loadCompensationData();
    }

    private void loadCompensationData() {
        String csvFile = "compensation.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String[]> compensationData = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                compensationData.add(data);
            }

            // Assuming the first row contains headers and the data starts from the second row
            String[] headers = compensationData.get(0);
            for (String[] data : compensationData.subList(1, compensationData.size())) {
                if (data.length >= 4) { // Ensure data is complete
                    String employeeNumber = data[0];
                    String fullName = data[1] + " " + data[2];
                    String position = data[3];
                    String hourlyRate = data[4]; // Assuming hourly rate is in the fifth column

                    employeeNumberLabel.setText(employeeNumber);
                    fullNameLabel.setText(fullName);
                    positionLabel.setText(position);
                    hourlyRateLabel.setText(hourlyRate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
