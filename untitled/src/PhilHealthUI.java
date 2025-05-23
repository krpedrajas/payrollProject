import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PhilHealthUI extends JFrame {
    JTable table;
    JScrollPane scrollPane;
    JPanel panel1;
    Container c;
    PhilHealthTableModel model;
    JLabel label;
    JTextField textField;

    public PhilHealthUI(ArrayList<Employee>employees){
        c = this.getContentPane();
        model=new PhilHealthTableModel();
        table=new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane=new JScrollPane(table);
        panel1= new JPanel();
        panel1.add(scrollPane);
        c.add(panel1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        label = new JLabel("Total Phil-Health:");
        textField = new JTextField();
        panel2.add(label);
        panel2.add(textField);
        c.add(panel2, BorderLayout.NORTH);
        double total =0;
        for (Employee employee: employees){
            total+=employee.getPhilHealth();
            model.addToTable(employee);
        }
        textField.setText(String.valueOf(total));
        textField.setEditable(false);

        this.setVisible(true);
        this.setTitle("Phil-Health Report");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
