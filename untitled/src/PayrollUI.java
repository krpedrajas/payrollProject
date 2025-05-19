import javax.swing.*;
import java.awt.*;

public class PayrollUI extends JFrame {

    JLabel employeeIdLabel, nameLabel, positionLabel, grossSalaryLabel, netSalaryLabel, deductionsLabel, workHoursLabel;
    JTextField employeeIdField, nameField, positionField, grossSalaryField, netSalaryField,deductionsField,workHoursField;
    JButton backB;
    Container c;
    PayrollTableModel model;
    JTable table;
    JPanel formPanel,buttonPanel;
    JScrollPane scrollPane;
    BorderLayout layout;


    public PayrollUI (Employee selectedEmployee){

        c = this.getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);

        employeeIdLabel = new JLabel("Employee ID:");
        nameLabel = new JLabel("Name:");
        positionLabel = new JLabel("Position:");
        grossSalaryLabel = new JLabel("Gross Salary:");
        netSalaryLabel = new JLabel("Net Salary:");
        deductionsLabel = new JLabel("Deductions:");
        workHoursLabel = new JLabel("Work Hours:");

        employeeIdField = new JTextField(15);
        employeeIdField.setText(selectedEmployee.getEmployeeId());
        nameField = new JTextField(15);
        nameField.setText(selectedEmployee.getName());
        positionField = new JTextField(15);
        positionField.setText(selectedEmployee.getPosition());
        grossSalaryField = new JTextField(10);
        grossSalaryField.setText(String.format("%.2f", selectedEmployee.getGrossPay()));
        netSalaryField = new JTextField(10);
        netSalaryField.setText(String.format("%.2f", selectedEmployee.getNetPay()));
        deductionsField = new JTextField(10);
        deductionsField.setText(String.format("%.2f", selectedEmployee.getDeductions()));
        workHoursField = new JTextField(10);
        workHoursField.setText(String.format("%.2f", selectedEmployee.getHoursAttended()));


        employeeIdField.setEditable(false);
        nameField.setEditable(false);
        positionField.setEditable(false);
        workHoursField.setEditable(false);
        grossSalaryField.setEditable(false);
        netSalaryField.setEditable(false);
        deductionsField.setEditable(false);

        backB = new JButton("Back");

        formPanel = new JPanel(new GridLayout(7, 2, 10 ,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(positionLabel);
        formPanel.add(positionField);
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

        //might not use table for payroll page anymore (not final do not delete)

//        model = new PayrollTableModel();
//        table = new JTable(model);
//        scrollPane = new JScrollPane(table);
//
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        c.add(topPanel, BorderLayout.NORTH);
//        c.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
        this.setTitle("Payroll");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        PayrollUI payrollUI=new PayrollUI(new Employee("1", "Kenneth Pedrajas", "Engineer", 500, 150));
    }
}
