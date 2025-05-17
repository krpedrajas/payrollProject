import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceUI extends JFrame {
    JLabel firstNameLabel, lastNameLabel, dateLabel, clockInLabel, clockOutLabel;
    JTextField firstNameField, lastNameField, dateField, clockInField, clockOutField;
    JButton clockInButton, clockOutButton, backButton;
    Container c;
    JPanel formPanel, buttonPanel;

    public AttendanceUI (){
        c = this. getContentPane();
        c.setLayout(new BorderLayout());

        firstNameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        dateLabel = new JLabel("Date Today: ");
        clockInLabel = new JLabel("Clock In: ");
        clockOutLabel = new JLabel("Clock Out: ");

        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        dateField = new JTextField(10);
        clockInField = new JTextField(10);
        clockOutField = new JTextField(10);

        dateField.setEditable(false);
        clockInField.setEditable(false);
        clockOutField.setEditable(false);

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateField.setText(today);

        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        backButton = new JButton("Back");

        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(clockInLabel);
        formPanel.add(clockInField);
        formPanel.add(clockOutLabel);
        formPanel.add(clockOutField);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);

        c.add(formPanel, BorderLayout.CENTER);
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
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                clockInField.setText(currentTime);
            }
        });

        clockOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                clockOutField.setText(currentTime);

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


                    Date clockInTime = sdf.parse(clockInField.getText());
                    Date clockOutTime = sdf.parse(clockOutField.getText());

                    long milliseconds = clockOutTime.getTime() - clockInTime.getTime();
                    double hoursWorked = milliseconds / (1000.0 * 60 * 60);

                    JOptionPane.showMessageDialog(AttendanceUI.this, "Hours worked: " + String.format("%.4f", hoursWorked));

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
        AttendanceUI attendanceUI = new AttendanceUI();
    }



}
