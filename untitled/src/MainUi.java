import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

//        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panel2.add(attendancechecking);
//        panel2.add(button1);
//        addToContainer(panel2,0,1);
//
//        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panel3.add(employeedetails);
//        panel3.add(button2);
//        addToContainer(panel3,0,2);
//
//        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panel4.add(reports);
//        panel4.add(button3);
//        addToContainer(panel4,0,3);

        addToContainer(button1,0,1);
        addToContainer(button2,0,2);
        addToContainer(button3,0,3);
        addToContainer(bwahaha,0,4);


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
