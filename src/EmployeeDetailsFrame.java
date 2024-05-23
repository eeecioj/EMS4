import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDetailsFrame extends JFrame {
    private String employeeNumber;
    private JPanel compensationPanel;

    public EmployeeDetailsFrame(String employeeNumber) {
        this.employeeNumber = employeeNumber;

        setTitle("Employee Details");
        setLayout(new BorderLayout());

        // Create and add the employee details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        add(detailsPanel, BorderLayout.NORTH);

        // Add employee details (you can customize this as needed)
        detailsPanel.add(new JLabel("Employee Number: " + employeeNumber));
        detailsPanel.add(new JLabel("Employee Name: " + getEmployeeName(employeeNumber))); // Assuming you have a method to get the employee name

        // Create and add the compensation panel
        compensationPanel = new JPanel();
        compensationPanel.setLayout(new BoxLayout(compensationPanel, BoxLayout.Y_AXIS));
        add(compensationPanel, BorderLayout.CENTER);

        // Calculate and display the salary breakdown
        displaySalaryBreakdown();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getEmployeeName(String employeeNumber) {
        // Dummy method to get employee name based on employee number
        return "John Doe";
    }

    private void displaySalaryBreakdown() {
        double totalHoursWorked = 0;
        double hourlyRate = 100; // Example hourly rate, you may fetch this from the employee details

        // Read timesheet data from CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("compensation.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(employeeNumber)) {
                    totalHoursWorked += Double.parseDouble(values[2]); // Assuming the hours worked is in the third column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double monthlySalary = totalHoursWorked * hourlyRate;
        double philHealthDeduction = monthlySalary * 0.03;
        double pagIbigDeduction = monthlySalary * 0.02;
        double sssDeduction = calculateSSSDeduction(monthlySalary);
        double taxableIncome = monthlySalary - (philHealthDeduction + pagIbigDeduction + sssDeduction);
        double withholdingTax = calculateWithholdingTax(taxableIncome);
        double totalDeductions = philHealthDeduction + pagIbigDeduction + sssDeduction + withholdingTax;
        double netSalary = monthlySalary - totalDeductions;

        compensationPanel.add(new JLabel("Total Hours Worked: " + totalHoursWorked));
        compensationPanel.add(new JLabel("Gross Income: Php " + monthlySalary));
        compensationPanel.add(new JLabel("PhilHealth: Php " + philHealthDeduction));
        compensationPanel.add(new JLabel("Pag-IBIG: Php " + pagIbigDeduction));
        compensationPanel.add(new JLabel("Social Security System: Php " + sssDeduction));
        compensationPanel.add(new JLabel("Withholding Tax: Php " + withholdingTax));
        compensationPanel.add(new JLabel("Total Deductions: Php " + totalDeductions));
        compensationPanel.add(new JLabel("Net Salary: Php " + netSalary));
    }

    private double calculateSSSDeduction(double monthlySalary) {
        if (monthlySalary >= 12250 && monthlySalary < 12750) {
            return 562.50;
        } else if (monthlySalary >= 12750 && monthlySalary < 13250) {
            return 585;
        } else if (monthlySalary >= 13250 && monthlySalary < 13750) {
            return 607.50;
        } else if (monthlySalary >= 13750 && monthlySalary < 14250) {
            return 630;
        } else if (monthlySalary >= 14250 && monthlySalary < 14750) {
            return 652.50;
        } else if (monthlySalary >= 14750 && monthlySalary < 15250) {
            return 675;
        } else if (monthlySalary >= 15250 && monthlySalary < 15750) {
            return 697.50;
        } else if (monthlySalary >= 15750 && monthlySalary < 16250) {
            return 720;
        } else if (monthlySalary >= 16250 && monthlySalary < 16750) {
            return 742.50;
        } else if (monthlySalary >= 16750 && monthlySalary < 17250) {
            return 765;
        } else if (monthlySalary >= 17250 && monthlySalary < 17750) {
            return 787.50;
        } else if (monthlySalary >= 17750 && monthlySalary < 18250) {
            return 810;
        } else if (monthlySalary >= 18250 && monthlySalary < 18750) {
            return 832.50;
        } else if (monthlySalary >= 18750 && monthlySalary < 19250) {
            return 855;
        } else if (monthlySalary >= 19250 && monthlySalary < 19750) {
            return 877.50;
        } else if (monthlySalary >= 19750 && monthlySalary < 20250) {
            return 900;
        } else if (monthlySalary >= 20250 && monthlySalary < 20750) {
            return 922.50;
        } else if (monthlySalary >= 20750 && monthlySalary < 21250) {
            return 945;
        } else if (monthlySalary >= 21250 && monthlySalary < 21750) {
            return 967.50;
        } else if (monthlySalary >= 21750 && monthlySalary < 22250) {
            return 990;
        } else if (monthlySalary >= 22250 && monthlySalary < 22750) {
            return 1012.50;
        } else if (monthlySalary >= 22750 && monthlySalary < 23250) {
            return 1035;
        } else if (monthlySalary >= 23250 && monthlySalary < 23750) {
            return 1057.50;
        } else if (monthlySalary >= 23750 && monthlySalary < 24250) {
            return 1080;
        } else if (monthlySalary >= 24250 && monthlySalary < 24750) {
            return 1102.50;
        } else if (monthlySalary >= 24750) {
            return 1125;
        }
        return 0;
    }

    private double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome >= 20833 && taxableIncome < 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
            return (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
            return (taxableIncome - 66667) * 0.30;
        }
        return 0;
    }

    public static void main(String[] args) {
        new EmployeeDetailsFrame("E12345"); // Example employee number
    }
}


