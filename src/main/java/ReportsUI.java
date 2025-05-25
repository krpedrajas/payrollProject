import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReportsUI extends JFrame {

    JButton sssButton, pagIbigButton, philHealthButton, birButton, yearEndTaxButton, backButton;
    JTextArea outputArea;
    Container c;
    public ReportsUI (ArrayList<Employee> employees) {
        c = this.getContentPane();
        c.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Reports", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.add(titleLabel, BorderLayout.NORTH);

        //buttons
        sssButton = new JButton("Generate SSS");
        pagIbigButton = new JButton("Generate Pag-Ibig");
        philHealthButton = new JButton("Generate Phil-Health");
        birButton = new JButton("Generate BIR");
        yearEndTaxButton = new JButton("Generate Year End Tax");

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        addButtonWithSpacing(buttonPanel, sssButton);
        addButtonWithSpacing(buttonPanel, pagIbigButton);
        addButtonWithSpacing(buttonPanel, philHealthButton);
        addButtonWithSpacing(buttonPanel, birButton);
        addButtonWithSpacing(buttonPanel, yearEndTaxButton);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(buttonPanel);

        c.add(centerWrapper, BorderLayout.NORTH);

        //back button
        backButton=new JButton("BACK");
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        backButtonPanel.add(backButton);
        c.add(backButton, BorderLayout.SOUTH);

        setTitle("Reports");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        sssButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SSSUI sssui = new SSSUI(employees);
                sssui.setVisible(true);

            }
        });

        pagIbigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagIbigUI pagIbigUI = new PagIbigUI(employees);
                pagIbigUI.setVisible(true);
            }
        });

        yearEndTaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaxUI taxUI = new TaxUI(employees);
                taxUI.setVisible(true);
            }
        });

        birButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BirUI birUI = new BirUI(employees);
                birUI.setVisible(true);
            }
        });

        philHealthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhilHealthUI philHealthUI = new PhilHealthUI(employees);
                philHealthUI.setVisible(true);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainUi mainUi=new MainUi(employees);
                ReportsUI.this.dispose();
            }
        });
    }

    private void addButtonWithSpacing(JPanel panel, JButton button) {
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(new Employee("1", "Kenneth Pedrajas", "DJ", 500));
        employees.get(0).setHoursAttended(55.604);
        employees.add(new Employee("2", "Ruan Justiniani", "Software Engineer", 350));
        employees.add(new Employee("3", "Jujin Ferrer", "Software Developer", 350));
        ReportsUI reportsUI=new ReportsUI(employees);
    }



}
