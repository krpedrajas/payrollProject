import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUi extends JFrame {

    JLabel title, bwahaha;
    JButton button1, button2, button3;
    Container container;
    GridBagLayout layout;

    public MainUi(){
        container=this.getContentPane();
        layout=new GridBagLayout();
        container.setLayout(layout);

        button1 = new JButton("ATTENDANCE CHECKING");
        button2 = new JButton("EMPLOYEE DETAILS");
        button3 = new JButton("REPORTS");

        title = new JLabel("PAYROLL SYSTEM");
        title.setFont(new Font("Poetsen One", Font.BOLD, 24));
        bwahaha = new JLabel("POWERED BY GLOBE TELECOM & RSJ GROUP OF COMPANIES");
        bwahaha.setFont(new Font("Roboto Slab", Font.PLAIN, 12));
        title.setPreferredSize(new Dimension(250, 100));



        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel1.add(title);
        addToContainer(panel1, 0,0);


        addToContainer(button1,0,1);
        addToContainer(button2,0,2);
        addToContainer(button3,0,3);
        addToContainer(bwahaha,0,4);

//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AttendanceUI attendanceUI = new AttendanceUI();
//                attendanceUI.setVisible(true);
//                MainUi.this.dispose();
//            }
//        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI employeeUI = new EmployeeUI();
                employeeUI.setVisible(true);
                MainUi.this.dispose();
            }
        });


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public  void addToContainer(Component component, int gridx, int gridy){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = gridx;
        c.gridy = gridy;
        container.add(component,c);
    }
}
