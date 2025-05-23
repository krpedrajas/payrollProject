import java.util.ArrayList;

public class Employee {

    // **Employee Management**
    //
    //   - Add, update, and delete employee records.
    //   - Store personal information, employment details, and salary information.
    //   - Track employee attendance and leaves.

    private String employeeId, name, position;
    private double hourlyRate;
    private double hoursAttended;
    private double deductions;
    private double grossPay;
    private double SSS;
    private double employerSSS;
    private double pagIbig;
    private double philHealth;
    private double withHoldingTax;
    private double netPay;



    public Employee(String employeeId, String name, String position, double salary){
        this.employeeId = employeeId;
        this.name = name;
        this.position=position;
        this.hourlyRate =salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getHoursAttended() {
        return hoursAttended;
    }

    public void setHoursAttended(double hoursAttended) {
        this.hoursAttended = hoursAttended;

        this.grossPay = hourlyRate * this.hoursAttended;
        this.SSS = computeSSS(grossPay);
        this.philHealth=computePhilhealth(grossPay);
        this.pagIbig = computePagibig(grossPay);
        this.withHoldingTax = computeWithholdingTax(grossPay);
        this.deductions = SSS + pagIbig + philHealth + withHoldingTax;
        this.netPay = grossPay - deductions;
    }



    //deduction Functions
    public double computePhilhealth(double grossPay){
        double philhealth;
        if(grossPay<10000){
            philhealth=250;
        }else if(grossPay>100000){
            philhealth=2500;
        }else{
            philhealth=grossPay*0.025;
        }
        System.out.println("PhilHealth: "+Math.round(philhealth*100.0)/100.0);
        return Math.round(philhealth*100.0)/100.0;
    }
    public double computePagibig(double monthlyIncome) {
        double contributionBase = Math.min(monthlyIncome, 5000); // Max base ₱5,000
        double pagibig = contributionBase * 0.02;                 // 2%
        System.out.println("Pagibig: "+Math.round(pagibig * 100.0) / 100.0);
        return Math.round(pagibig * 100.0) / 100.0;
    }
    public double computeSSS(double grossPay) {
//        double msc = Math.min(monthlyIncome, 30000); // Cap at ₱30,000
//        double employeeShare = msc * 0.045;          // 4.5% of MSC
//        System.out.println("SSS: "+Math.round(employeeShare * 100.0) / 100.0);
//        return Math.round(employeeShare * 100.0) / 100.0;
        double msc;
        double employerSS;
        double ec;
        double employeeSS;

        //get msc
        if(grossPay<5250){
            msc=5000;
            System.out.println("MSC: "+msc);
        }else if (grossPay>=34750){
            msc=35000;
            System.out.println("MSC: "+msc);
        }else{
            //round to nearest multiple of 500
            msc=Math.round(grossPay/500.0)*500;
            System.out.println("MSC: "+msc);
        }

        //get ec (for employer)
        if(msc<=14500){
            ec=10;
        }else{
            ec=30;
        }

        //ss employee (5% of msc)
        employeeSS=msc*0.05;
        System.out.println("Employee SSS: "+employeeSS);

        //ss employer (10% of msc + ec)
        this.employerSSS=msc*0.10+ec;
        System.out.println("Employer SSS: "+this.employerSSS);

        System.out.println("Total SSS: "+(employeeSS+this.employerSSS));
        return employeeSS;
    }

    public double computeWithholdingTax(double monthlyIncome) {
        double tax;

        double nonTaxableContributions=this.getPhilHealth()+this.getPagIbig()+this.getSSS();
        System.out.println("Non taxable contributions: "+nonTaxableContributions);
        monthlyIncome-=nonTaxableContributions;
        System.out.println("Taxable Income: "+monthlyIncome);

        if (monthlyIncome <= 20833) {
            tax = 0;
        } else if (monthlyIncome <= 33332) {
//            tax = (monthlyIncome - 20833) * 0.20;
            double taxableIncome=monthlyIncome-20833;
            tax=taxableIncome*0.15;
        } else if (monthlyIncome <= 66666) {
//            tax = 2500 + (monthlyIncome - 33333) * 0.25;
            double taxableIncome=monthlyIncome-33333;
            tax=1875+taxableIncome*0.20;
        } else if (monthlyIncome <= 166666) {
//            tax = 10833 + (monthlyIncome - 66667) * 0.30;
            double taxableIncome=monthlyIncome-66667;
            tax=8541.80+taxableIncome*0.25;
        } else if (monthlyIncome <= 666666) {
//            tax = 40833 + (monthlyIncome - 166667) * 0.32;
            double taxableIncome=monthlyIncome-166667;
            tax=33541.80+taxableIncome*0.30;
        } else {
//            tax = 200833 + (monthlyIncome - 666667) * 0.35;
            double taxableIncome=monthlyIncome-666667;
            tax=183541.80+taxableIncome*0.35;
        }
        System.out.println("Tax: "+Math.round(tax * 100.0) / 100.0);
        return Math.round(tax * 100.0) / 100.0;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getPhilHealth() {
        return philHealth;
    }

    public void setPhilHealth(double philHealth) {
        this.philHealth = philHealth;
    }

    public double getSSS() {
        return SSS;
    }

    public void setSSS(double SSS) {
        this.SSS = SSS;
    }

    public double getPagIbig() {
        return pagIbig;
    }

    public void setPagIbig(double pagIbig) {
        this.pagIbig = pagIbig;
    }

    public double getWithHoldingTax() {
        return withHoldingTax;
    }

    public void setWithHoldingTax(double withHoldingTax) {
        this.withHoldingTax = withHoldingTax;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
