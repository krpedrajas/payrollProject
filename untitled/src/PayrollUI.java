import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayrollUI extends JFrame{

    JLabel employeeIdLabel, nameLabel, positionLabel, grossSalaryLabel, netSalaryLabel, deductionsLabel, workHoursLabel, hourlyRateLabel;
    JLabel philHealthLabel, pagIbigLabel, sssLabel, taxLabel;
    JTextField employeeIdField, nameField, positionField, grossSalaryField, netSalaryField,deductionsField,workHoursField, hourlyRateField;
    JTextField philHealthField, pagIbigField, sssField, taxField;
    JButton backB;
    Container c;
    PayrollTableModel model;
    JTable table;
    JPanel formPanel,buttonPanel, deductionsPanel;
    JScrollPane scrollPane;
    BorderLayout layout;


    public PayrollUI (Employee selectedEmployee){

        c = this.getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);

        employeeIdLabel = new JLabel("Employee ID:");
        nameLabel = new JLabel("Name:");
        positionLabel = new JLabel("Position:");
        hourlyRateLabel=new JLabel("Hourly Rate:");
        workHoursLabel = new JLabel("Work Hours:");
        grossSalaryLabel = new JLabel("Gross Salary:");
        netSalaryLabel = new JLabel("Net Salary:");
        deductionsLabel = new JLabel("Deductions:");

        employeeIdField = new JTextField(15);
        employeeIdField.setText(selectedEmployee.getEmployeeId());
        nameField = new JTextField(15);
        nameField.setText(selectedEmployee.getName());
        positionField = new JTextField(15);
        positionField.setText(selectedEmployee.getPosition());
        hourlyRateField=new JTextField(10);
        hourlyRateField.setText(String.format("%.2f", selectedEmployee.getHourlyRate()));
        workHoursField = new JTextField(10);
        workHoursField.setText(String.format("%.2f", selectedEmployee.getHoursAttended()));
        grossSalaryField = new JTextField(10);
        grossSalaryField.setText(String.format("%.2f", selectedEmployee.getGrossPay()));
        netSalaryField = new JTextField(10);
        netSalaryField.setText(String.format("%.2f", selectedEmployee.getNetPay()));
        deductionsField = new JTextField(10);
        deductionsField.setText(String.format("%.2f", selectedEmployee.getDeductions()));


        employeeIdField.setEditable(false);
        nameField.setEditable(false);
        positionField.setEditable(false);
        hourlyRateField.setEditable(false);
        workHoursField.setEditable(false);
        grossSalaryField.setEditable(false);
        netSalaryField.setEditable(false);
        deductionsField.setEditable(false);

        backB = new JButton("Back");

        formPanel = new JPanel(new GridLayout(8, 2, 10 ,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(positionLabel);
        formPanel.add(positionField);
        formPanel.add(hourlyRateLabel);
        formPanel.add(hourlyRateField);
        formPanel.add(workHoursLabel);
        formPanel.add(workHoursField);
        formPanel.add(grossSalaryLabel);
        formPanel.add(grossSalaryField);
        formPanel.add(deductionsLabel);
        formPanel.add(deductionsField);
        formPanel.add(netSalaryLabel);
        formPanel.add(netSalaryField);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(backB);

        deductionsPanel = new JPanel(new GridLayout(4, 2, 10 ,10));
        deductionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        philHealthLabel=new JLabel("PhilHealth:");
        pagIbigLabel=new JLabel("Pag-ibig:");
        sssLabel=new JLabel("SSS:");
        taxLabel=new JLabel("Withholding Tax:");

        philHealthField=new JTextField(15);
        pagIbigField=new JTextField(15);
        sssField=new JTextField(15);
        taxField=new JTextField(15);

        philHealthField.setText(String.format("%.2f", selectedEmployee.getPhilHealth()));
        philHealthField.setEditable(false);
        pagIbigField.setText(String.format("%.2f", selectedEmployee.getPagIbig()));
        pagIbigField.setEditable(false);
        sssField.setText(String.format("%.2f", selectedEmployee.getSSS()));
        sssField.setEditable(false);
        taxField.setText(String.format("%.2f", selectedEmployee.getWithHoldingTax()));
        taxField.setEditable(false);

        deductionsPanel.add(philHealthLabel);
        deductionsPanel.add(philHealthField);
        deductionsPanel.add(pagIbigLabel);
        deductionsPanel.add(pagIbigField);
        deductionsPanel.add(sssLabel);
        deductionsPanel.add(sssField);
        deductionsPanel.add(taxLabel);
        deductionsPanel.add(taxField);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(deductionsPanel, BorderLayout.SOUTH);

        c.add(topPanel, BorderLayout.NORTH);

        this.setVisible(true);
        this.setTitle("Payroll");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PayrollUI.this.dispose();
            }
        });
    }

    public static void main(String[] args) {

        Employee employee=new Employee("1", "Kenneth Pedrajas", "Engineer", 500);
        employee.setHoursAttended(150);

        PayrollUI payrollUI=new PayrollUI(employee);
    }
}
