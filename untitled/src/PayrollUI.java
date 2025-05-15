import javax.swing.*;
import java.awt.*;

public class PayrollUI extends JFrame {

    JLabel firstName, lastName, position, grossSalary, netSalary, deductions, workHours;
    JTextField firstNameField, lastNameField, positionField, gorssSalaryField, netSalaryField,deductionsField,workHoursField;
    JButton nextB,backB,saveB;
    Container c;
    PayrollTableModel model;
    JTable table;
    JPanel formPanel,buttonPanel;


    public PayrollUI (){

        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        position = new JLabel("Position:");
        grossSalary = new JLabel("Gross Salary:");
        netSalary = new JLabel("Net Salary:");
        deductions = new JLabel("Deductions:");
        workHours = new JLabel("Work Hours:");

        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        positionField = new JTextField(15);
        gorssSalaryField = new JTextField(10);
        netSalaryField = new JTextField(10);
        deductionsField = new JTextField(10);
        workHoursField = new JTextField(10);

        gorssSalaryField.setEditable(false);
        netSalaryField.setEditable(false);
        deductionsField.setEditable(false);

        nextB = new JButton("Next");
        backB = new JButton("Back");
        saveB = new JButton("Save");

        formPanel = new JPanel(new GridLayout(7, 2, 10 ,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(firstName);
        formPanel.add(firstNameField);
        formPanel.add(lastName);
        formPanel.add(lastNameField);
        formPanel.add(position);
        formPanel.add(positionField);
        formPanel.add(workHours);
        formPanel.add(workHoursField);
        formPanel.add(grossSalary);
        formPanel.add(gorssSalaryField);
        formPanel.add(deductions);
        formPanel.add(deductionsField);
        formPanel.add(netSalary);
        formPanel.add(netSalaryField);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(backB);
        buttonPanel.add(saveB);
        buttonPanel.add(nextB);

        model = new PayrollTableModel();
        table = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(table);

        c.add(formPanel, BorderLayout.NORTH);
        c.add(buttonPanel, BorderLayout.CENTER);
        c.add(tableScroll, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setTitle("Employee Page");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

}
