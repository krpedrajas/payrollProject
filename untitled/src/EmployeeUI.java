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
        addButton.addActionListener(this);
        buttonsPanel.add(addButton);
        deleteButton=new JButton("DELETE");
        deleteButton.addActionListener(this);
        buttonsPanel.add(deleteButton);
        updateButton=new JButton("UPDATE");
        updateButton.addActionListener(this);
        buttonsPanel.add(updateButton);

        container.add(buttonsPanel, BorderLayout.CENTER);

        tableModel=new EmployeeTableModel();
        table=new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        if(e.getSource()==addButton){
            String firstName=firstNameField.getText();
            String lastName=lastNameField.getText();
            String position=positionField.getText();
            String salary= salaryField.getText();
            if(firstName.isEmpty()||lastName.isEmpty()||position.isEmpty()||salary.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }else {
                double s=Double.parseDouble(salary);
                tableModel.addToTable(new Employee(firstName, lastName, position, s));
                firstNameField.setText("");
                lastNameField.setText("");
                positionField.setText("");
                salaryField.setText("");
            }
        }else if (e.getSource()==deleteButton){
            if(table.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null, "No row was selected.");
            }else {
                tableModel.deleteFromTable(table.getSelectedRows());
                firstNameField.setText("");
                lastNameField.setText("");
                positionField.setText("");
                salaryField.setText("");
            }
        }else if(e.getSource()==updateButton){
            String firstName=firstNameField.getText();
            String lastName=lastNameField.getText();
            String position=positionField.getText();
            String salary= salaryField.getText();

            if(table.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null, "No row was selected.");
            }else {
                tableModel.editSelectedRow(table.getSelectedRow(), firstName, lastName, position, salary);
                firstNameField.setText("");
                lastNameField.setText("");
                positionField.setText("");
                salaryField.setText("");
            }

        }
    }

    public static void main(String[] args) {
        EmployeeUI ui=new EmployeeUI("Employee UI");
    }
}
