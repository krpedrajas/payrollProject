import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// **Employee Management**
//
//   - Add, update, and delete employee records.
//   - Store personal information, employment details, and salary information.
//   - Track employee attendance and leaves. (might have to make separate UI to show attendance records/history)

public class EmployeeUI extends JFrame implements ActionListener {

    JTable table;
    EmployeeTableModel tableModel;
    JButton addButton, deleteButton, updateButton;
    JTextField firstNameField, lastNameField, positionField, salaryField;
    JLabel firstNameLabel, lastNameLabel, positionLabel, salaryLabel;
    JPanel infoPanel, buttonsPanel;
    Container container;
    BorderLayout layout;

    public EmployeeUI(String title){
        container=this.getContentPane();
        layout=new BorderLayout();
        container.setLayout(layout);


        infoPanel=new JPanel(new GridBagLayout());

        firstNameLabel=new JLabel("First Name:");
        firstNameField=new JTextField(10);
        addToPanel(firstNameLabel, 0,0,1);
        addToPanel(firstNameField, 1,0,1);
        lastNameLabel=new JLabel("Last Name:");
        lastNameField=new JTextField(10);
        addToPanel(lastNameLabel, 2,0,1);
        addToPanel(lastNameField, 3,0,1);
        positionLabel=new JLabel("Position:");
        positionField=new JTextField(10);
        addToPanel(positionLabel, 0,1,1);
        addToPanel(positionField, 1,1,1);
        salaryLabel=new JLabel("Salary:");
        salaryField=new JTextField(10);
        addToPanel(salaryLabel, 2,1,1);
        addToPanel(salaryField, 3,1,1);

        container.add(infoPanel, BorderLayout.NORTH);

        buttonsPanel=new JPanel(new FlowLayout());

        addButton=new JButton("ADD");
        buttonsPanel.add(addButton);
        deleteButton=new JButton("DELETE");
        buttonsPanel.add(deleteButton);
        updateButton=new JButton("UPDATE");
        buttonsPanel.add(updateButton);

        container.add(buttonsPanel, BorderLayout.CENTER);

        tableModel=new EmployeeTableModel();
        table=new JTable(tableModel);
        JScrollPane scrollPane=new JScrollPane(table);
        container.add(scrollPane, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setTitle("Employee Page");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addToPanel(Component component, int gridx, int gridy, int gridW) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridW;
        infoPanel.add(component, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        EmployeeUI ui=new EmployeeUI("Employee UI");
    }
}
