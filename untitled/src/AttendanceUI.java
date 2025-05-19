import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceUI extends JFrame {
    JLabel employeeIdLabel, dateLabel, clockInLabel, clockOutLabel, timeLabel;
    JTextField employeeIdField, dateField, clockInField, clockOutField, timeField;
    JButton clockInButton, clockOutButton, backButton;
    Container c;
    JPanel formPanel, timePanel, buttonPanel;

    public AttendanceUI (ArrayList<Employee> employees){
//        this.employees = employees;
        c = this. getContentPane();
        c.setLayout(new BorderLayout());

        employeeIdLabel = new JLabel("Employee ID: ");
        dateLabel = new JLabel("Date Today: ");
        clockInLabel = new JLabel("Clock In: ");
        clockOutLabel = new JLabel("Clock Out: ");

        employeeIdField = new JTextField(15);
        dateField = new JTextField(10);
        clockInField = new JTextField(10);
        clockOutField = new JTextField(10);

        clockInField.setEditable(false);
        clockOutField.setEditable(false);

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateField.setText(today);

        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        backButton = new JButton("Back");

        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(clockInLabel);
        formPanel.add(clockInField);
        formPanel.add(clockOutLabel);
        formPanel.add(clockOutField);
        //
        timeLabel=new JLabel("Time:");
        timeField=new JTextField(4);
        timePanel=new JPanel(new FlowLayout());
        timePanel.add(timeLabel);
        timePanel.add(timeField);
        //
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);

        c.add(formPanel, BorderLayout.NORTH);
        c.add(timePanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);

        this.setTitle("AttendanceUI");
        this.pack();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        clockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockInField.setText(timeField.getText());
            }
        });

        clockOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clockOutField.setText(timeField.getText());

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");



                    Date clockInTime = sdf.parse(clockInField.getText());
                    Date clockOutTime = sdf.parse(clockOutField.getText());

                    long milliseconds = clockOutTime.getTime() - clockInTime.getTime();
                    double hoursWorked = milliseconds / (1000.0 * 60 * 60);

                    String employeeId = employeeIdField.getText().trim();

                    Employee matchedEmployee = null;

                    for(Employee employee : employees){
                        if(employee.getEmployeeId().equals(employeeId)){
                            matchedEmployee = employee;
                            break;
                        }
                    }

                    if(matchedEmployee != null){
                        double totalHours = matchedEmployee.getHoursAttended() + hoursWorked;
                        matchedEmployee.setHoursAttended(totalHours);

                        JOptionPane.showMessageDialog(AttendanceUI.this,
                                "Clocked out.\nHours worked: " + String.format("%.4f", hoursWorked)+
                                "\nTotal hours for " + matchedEmployee.getName() +
                                ": " + String.format("%.4f", totalHours));
                    }else{
                        JOptionPane.showMessageDialog(AttendanceUI.this,
                                "Employee not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please clock in before clocking out.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceUI.this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(new Employee("1", "Kenneth Pedrajas", "Engineer", 500));
        AttendanceUI attendanceUI=new AttendanceUI(employees);
    }


}
