import javax.swing.*;
import java.awt.*;
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
        
    }


}
