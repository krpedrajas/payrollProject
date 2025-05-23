import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //labels
        employeeIdLabel = new JLabel("Employee ID: ");
        dateLabel = new JLabel("Date (YYYY-MM-DD): ");
        clockInLabel = new JLabel("Clock In: ");
        clockOutLabel = new JLabel("Clock Out: ");

        //fields
        employeeIdField = new JTextField(15);
        dateField = new JTextField(10);
        clockInField = new JTextField(10);
        clockOutField = new JTextField(10);

        //clock in/out
        clockInField.setEditable(false);
        clockOutField.setEditable(false);

        //date
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateField.setText(today);

        //fields to panel
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

        //time
        timeLabel=new JLabel("Time:");
        timeField=new JTextField(4);
        timePanel=new JPanel(new FlowLayout());
        timePanel.add(timeLabel);
        timePanel.add(timeField);

        //buttons
        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        clockOutButton.setEnabled(false);
        backButton = new JButton("Back");
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
                //validate date
                String date=dateField.getText();
                if(!isValidDate(date)){
                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //find employee from list of employees
                String employeeId = employeeIdField.getText().trim();
                Employee matchedEmployee = null;
                for(Employee employee : employees){
                    if(employee.getEmployeeId().equals(employeeId)){
                        matchedEmployee = employee;
                        break;
                    }
                }
                if(matchedEmployee==null){
                    JOptionPane.showMessageDialog(AttendanceUI.this,
                            "Employee not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //check if time inputted is 24:00 (clock in only allows until 23:59)
                if(timeField.getText().equals("24:00")){
                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid clock in time.", "Invalid Clock In Time", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //time fields to SimpleDateFormat
                Date clockInTime = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    clockInTime = sdf.parse(timeField.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid clock in time.", "Invalid Clock In Time", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clockInField.setText(timeField.getText());

                //enable clock out, disable clock in and back
                clockOutButton.setEnabled(true);
                clockInButton.setEnabled(false);
                backButton.setEnabled(false);

                //disable employeeId and date fields
                employeeIdField.setEditable(false);
                dateField.setEditable(false);

                //clear time field
                timeField.setText("");
            }
        });

        clockOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //time fields to SimpleDateFormat
                Date clockOutTime = null;
                Date clockInTime = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    clockInTime = sdf.parse(clockInField.getText());
                    clockOutTime = sdf.parse(timeField.getText());
                    if(clockOutTime.before(clockInTime)){
                        JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid clock out time.", "Invalid Clock Out Time", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid clock out time.", "Invalid Clock Out Time", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clockOutField.setText(timeField.getText());

                //calculate hours worked
                long milliseconds = clockOutTime.getTime() - clockInTime.getTime();
                double hoursWorked = milliseconds / (1000.0 * 60 * 60);

                //find employee from list of employees
                String employeeId = employeeIdField.getText().trim();
                Employee matchedEmployee = null;
                for (Employee employee : employees) {
                    if (employee.getEmployeeId().equals(employeeId)) {
                        matchedEmployee = employee;
                        break;
                    }
                }

                //add hours worked to employee's total hours

                double totalHours = matchedEmployee.getHoursAttended() + hoursWorked;
                matchedEmployee.setHoursAttended(totalHours);
                JOptionPane.showMessageDialog(AttendanceUI.this,
                        "Clocked out." +
                                "\nHours worked: " + String.format("%.4f", hoursWorked) +
                                "\nTotal hours for " + matchedEmployee.getName() + ": " + String.format("%.4f", totalHours));

                //disable clock out, enable clock in
                clockOutButton.setEnabled(false);
                clockInButton.setEnabled(true);

                //clear all fields (except date)
                employeeIdField.setText("");
//                    dateField.setText("");
                clockInField.setText("");
                clockOutField.setText("");
                timeField.setText("");

                //enable employeeId and date field
                employeeIdField.setEditable(true);
                dateField.setEditable(true);

//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(AttendanceUI.this, "Please enter a valid clock out time.", "Invalid Clock Out Time", JOptionPane.ERROR_MESSAGE);
//                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceUI.this.dispose();
            }
        });
    }

    private boolean isValidDate(String date) {
        // Regex to validate YYYY-MM-DD including leap years
        String regex = "^((19|20)\\d\\d)-"
                + "((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01])|"
                + "(0[469]|11)-(0[1-9]|[12][0-9]|30)|"
                + "02-(0[1-9]|1[0-9]|2[0-8])|"
                + "02-29(?=-(?:19|20)(?:[02468][048]|[13579][26])$))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private boolean isValidTime(String time) {
        String regex = "^([01][0-9]|2[0-3]):([0-5][0-9])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(new Employee("1", "Kenneth Pedrajas", "DJ", 500));
        employees.add(new Employee("2", "Ruan Justiniani", "Software Engineer", 350));
        employees.add(new Employee("3", "Jujin Ferrer", "Software Developer", 350));
        AttendanceUI attendanceUI=new AttendanceUI(employees);
    }


}
