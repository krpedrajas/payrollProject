public class Employee {

    // **Employee Management**
    //
    //   - Add, update, and delete employee records.
    //   - Store personal information, employment details, and salary information.
    //   - Track employee attendance and leaves.

    private String firstName, lastName, position;
    private double hourlyRate;
    private int hoursAttended;
    private double deductions;
    private double salaryCap;
    private double grossPay;
    private double SSS;
    private double pagIbig;
    private double withHoldingTax;
    private double netPay;



    public Employee(String firstName, String lastName, String position, double salary){
        this.firstName=firstName;
        this.lastName=lastName;
        this.position=position;
        this.hourlyRate =salary;
    }
    public Employee(String firstName, String lastName, String position, double salary, double hoursAttended){
        this.firstName=firstName;
        this.lastName=lastName;
        this.position=position;
        this.hourlyRate =salary;
        this.hoursAttended = (int)hoursAttended;

        this.grossPay = hourlyRate * this.hoursAttended;
        this.SSS = computeSSS(grossPay,salaryCap);
        this.pagIbig = computePagibig(grossPay);
        this.withHoldingTax = computeWithholdingTax(grossPay);

        this.deductions = SSS + pagIbig + withHoldingTax;
        this.netPay = grossPay - deductions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursAttended() {
        return hoursAttended;
    }

    public void setHoursAttended(int hoursAttended) {
        this.hoursAttended = hoursAttended;
    }



    //deduction Functions
    public static double computePagibig(double monthlyIncome) {
        double contributionBase = Math.min(monthlyIncome, 5000); // Max base ₱5,000
        double pagibig = contributionBase * 0.02;                 // 2%
        return Math.round(pagibig * 100.0) / 100.0;
    }
    public static double computeSSS(double monthlyIncome, double salaryCap) {
        double msc = Math.min(monthlyIncome, salaryCap); // Cap at ₱30,000
        double employeeShare = msc * 0.045;          // 4.5% of MSC
        return Math.round(employeeShare * 100.0) / 100.0;
    }

    public static double computeWithholdingTax(double monthlyIncome) {
        double tax;

        if (monthlyIncome <= 20833) {
            tax = 0;
        } else if (monthlyIncome <= 33332) {
            tax = (monthlyIncome - 20833) * 0.20;
        } else if (monthlyIncome <= 66666) {
            tax = 2500 + (monthlyIncome - 33333) * 0.25;
        } else if (monthlyIncome <= 166666) {
            tax = 10833 + (monthlyIncome - 66667) * 0.30;
        } else if (monthlyIncome <= 666666) {
            tax = 40833 + (monthlyIncome - 166667) * 0.32;
        } else {
            tax = 200833 + (monthlyIncome - 666667) * 0.35;
        }

        return Math.round(tax * 100.0) / 100.0;
    }

    public double getSalaryCap() {
        return salaryCap;
    }

    public void setSalaryCap(double salaryCap) {
        this.salaryCap = salaryCap;
    }
}
