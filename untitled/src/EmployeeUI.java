import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// **Employee Management**
//
//   - Add, update, and delete employee records.
//   - Store personal information, employment details, and salary information.
//   - Track employee attendance and leaves. (might have to make separate UI to show attendance records/history)

public class EmployeeUI extends JFrame{

    JTable table;
    EmployeeTableModel tableModel;
    JButton addButton, deleteButton, updateButton, toPayRollButton, backButton;
    JTextField employeeIdField, nameField, positionField, hourlyRateField;
    JLabel employeeIdLabel, nameLabel, positionLabel, hourlyRateLabel;
    JPanel infoPanel, buttonsPanel;
    Container container;
    BorderLayout layout;

    public EmployeeUI(ArrayList<Employee> employees){
        container=this.getContentPane();
        layout=new BorderLayout();
        container.setLayout(layout);


        infoPanel=new JPanel(new GridBagLayout());

        employeeIdLabel =new JLabel("Employee ID:");
        employeeIdField =new JTextField(10);
        addToPanel(employeeIdLabel, 0,0,1);
        addToPanel(employeeIdField, 1,0,1);
        nameLabel =new JLabel("Name:");
        nameField =new JTextField(10);
        addToPanel(nameLabel, 2,0,1);
        addToPanel(nameField, 3,0,1);
        positionLabel=new JLabel("Position:");
        positionField=new JTextField(10);
        addToPanel(positionLabel, 0,1,1);
        addToPanel(positionField, 1,1,1);
        hourlyRateLabel =new JLabel("Hourly Rate:");
        hourlyRateField =new JTextField(10);
        addToPanel(hourlyRateLabel, 2,1,1);
        addToPanel(hourlyRateField, 3,1,1);

        container.add(infoPanel, BorderLayout.NORTH);

        buttonsPanel=new JPanel(new FlowLayout());

        backButton=new JButton("BACK");
        buttonsPanel.add(backButton);
        addButton=new JButton("ADD");
//        addButton.addActionListener(this);
        buttonsPanel.add(addButton);
        deleteButton=new JButton("DELETE");
//        deleteButton.addActionListener(this);
        buttonsPanel.add(deleteButton);
        updateButton=new JButton("UPDATE");
//        updateButton.addActionListener(this);
        buttonsPanel.add(updateButton);
        toPayRollButton=new JButton("TO PAYROLL");
        buttonsPanel.add(toPayRollButton);

        container.add(buttonsPanel, BorderLayout.CENTER);

        tableModel=new EmployeeTableModel();
        table=new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane=new JScrollPane(table);
        container.add(scrollPane, BorderLayout.SOUTH);

        //add existing employees to table
        for (Employee employee: employees){
            tableModel.addToTable(employee);
        }

        this.setVisible(true);
        this.setTitle("Employee Details");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        toPayRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1){
                    Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);

                    PayrollUI payrollUI = new PayrollUI(selectedEmployee);
                    payrollUI.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(EmployeeUI.this, "Please select an employee", "NO EMPLOYEE SELECTED" , JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainUi mainUi=new MainUi(employees);
                EmployeeUI.this.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId= employeeIdField.getText();
                String name= nameField.getText();
                String position=positionField.getText();
                String salary= hourlyRateField.getText();

                if(employeeId.isEmpty()||name.isEmpty()||position.isEmpty()||salary.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }else {
                    double s=Double.parseDouble(salary);
                    Employee employee=new Employee(employeeId, name, position, s);
                    employees.add(employee);
                    tableModel.addToTable(employee);
                    employeeIdField.setText("");
                    nameField.setText("");
                    positionField.setText("");
                    hourlyRateField.setText("");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(EmployeeUI.this, "Please select an employee", "NO EMPLOYEE SELECTED" , JOptionPane.WARNING_MESSAGE);
                }else {
                    //need to update employee arraylist
                    tableModel.deleteFromTable(table.getSelectedRows());
                    employeeIdField.setText("");
                    nameField.setText("");
                    positionField.setText("");
                    hourlyRateField.setText("");
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //need to update employee arraylist
                String employeeId= employeeIdField.getText();
                String name= nameField.getText();
                String position=positionField.getText();
                String salary= hourlyRateField.getText();

                if(table.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(EmployeeUI.this, "Please select an employee", "NO EMPLOYEE SELECTED" , JOptionPane.WARNING_MESSAGE);
                }else {
                    tableModel.editSelectedRow(table.getSelectedRow(), employeeId, name, position, salary);
                    employeeIdField.setText("");
                    nameField.setText("");
                    positionField.setText("");
                    hourlyRateField.setText("");
                }
            }
        });
    }

    public void addToPanel(Component component, int gridx, int gridy, int gridW) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridW;
        infoPanel.add(component, gbc);
    }

    public static void main(String[] args) {

        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(new Employee("1", "Kenneth Pedrajas", "DJ", 500));
//        employees.add(new Employee("2", "Ruan Justiniani", "Software Engineer", 350));
//        employees.add(new Employee("3", "Jujin Ferrer", "Software Developer", 350));

        EmployeeUI ui=new EmployeeUI(employees);
    }
}
