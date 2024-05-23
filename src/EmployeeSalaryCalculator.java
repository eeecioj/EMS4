public class EmployeeSalaryCalculator {

    public static double computeMonthlySalary(double hourlyRate, double totalHoursWorked) {
        // Calculate weekly and monthly salary
        double weeklySalary = hourlyRate * totalHoursWorked;
        double monthlySalary = weeklySalary * 4; // Assuming 4 weeks in a month

        // Calculate deductions
        double philHealthDeduction = monthlySalary * 0.03; // 3% of monthly salary
        double pagIbigDeduction = monthlySalary * 0.02; // 2% of monthly salary
        double sssDeduction = calculateSSSDeduction(monthlySalary);
        double taxableIncome = monthlySalary - (philHealthDeduction + pagIbigDeduction + sssDeduction);
        double withholdingTax = calculateWithholdingTax(taxableIncome);

        double totalDeductions = philHealthDeduction + pagIbigDeduction + sssDeduction + withholdingTax;
        double netSalary = monthlySalary - totalDeductions;

        return netSalary;
    }

    private static double calculateSSSDeduction(double monthlySalary) {
        double sssDeduction = 0;
        if (monthlySalary >= 12250 && monthlySalary < 12750) {
            sssDeduction = 562.50;
        } else if (monthlySalary >= 12750 && monthlySalary < 13250) {
            sssDeduction = 585;
        } else if (monthlySalary >= 13250 && monthlySalary < 13750) {
            sssDeduction = 607.50;
        } else if (monthlySalary >= 13750 && monthlySalary < 14250) {
            sssDeduction = 630;
        } else if (monthlySalary >= 14250 && monthlySalary < 14750) {
            sssDeduction = 652.50;
        } else if (monthlySalary >= 14750 && monthlySalary < 15250) {
            sssDeduction = 675;
        } else if (monthlySalary >= 15250 && monthlySalary < 15750) {
            sssDeduction = 697.50;
        } else if (monthlySalary >= 15750 && monthlySalary < 16250) {
            sssDeduction = 720;
        } else if (monthlySalary >= 16250 && monthlySalary < 16750) {
            sssDeduction = 742.50;
        } else if (monthlySalary >= 16750 && monthlySalary < 17250) {
            sssDeduction = 765;
        } else if (monthlySalary >= 17250 && monthlySalary < 17750) {
            sssDeduction = 787.50;
        } else if (monthlySalary >= 17750 && monthlySalary < 18250) {
            sssDeduction = 810;
        } else if (monthlySalary >= 18250 && monthlySalary < 18750) {
            sssDeduction = 832.50;
        } else if (monthlySalary >= 18750 && monthlySalary < 19250) {
            sssDeduction = 855;
        } else if (monthlySalary >= 19250 && monthlySalary < 19750) {
            sssDeduction = 877.50;
        } else if (monthlySalary >= 19750 && monthlySalary < 20250) {
            sssDeduction = 900;
        } else if (monthlySalary >= 20250 && monthlySalary < 20750) {
            sssDeduction = 922.50;
        } else if (monthlySalary >= 20750 && monthlySalary < 21250) {
            sssDeduction = 945;
        } else if (monthlySalary >= 21250 && monthlySalary < 21750) {
            sssDeduction = 967.50;
        } else if (monthlySalary >= 21750 && monthlySalary < 22250) {
            sssDeduction = 990;
        } else if (monthlySalary >= 22250 && monthlySalary < 22750) {
            sssDeduction = 1012.50;
        } else if (monthlySalary >= 22750 && monthlySalary < 23250) {
            sssDeduction = 1035;
        } else if (monthlySalary >= 23250 && monthlySalary < 23750) {
            sssDeduction = 1057.50;
        } else if (monthlySalary >= 23750 && monthlySalary < 24250) {
            sssDeduction = 1080;
        } else if (monthlySalary >= 24250 && monthlySalary < 24750) {
            sssDeduction = 1102.50;
        } else if (monthlySalary >= 24750) {
            sssDeduction = 1125;
        }
        return sssDeduction;
    }

    private static double calculateWithholdingTax(double taxableIncome) {
        double withholdingTax = 0;
        if (taxableIncome >= 20833 && taxableIncome < 33333) {
            withholdingTax = (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
            withholdingTax = (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
            withholdingTax = (taxableIncome - 33333) * 0.30;
        } else if (taxableIncome >= 166667 && taxableIncome < 666667) {
            withholdingTax = (taxableIncome - 33333) * 0.32;
        } else if (taxableIncome >= 666667) {
            withholdingTax = (taxableIncome - 33333) * 0.35;
        }
        return withholdingTax;
    }
}