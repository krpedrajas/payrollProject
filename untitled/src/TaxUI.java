import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaxUI extends JFrame {
    JTable table;
    JScrollPane scrollPane;
    JPanel panel1;
    Container c;
    TaxTableModel model;
    JLabel label;
    JTextField textField;

    public TaxUI(ArrayList<Employee> employees) {
        c = this.getContentPane();
        model = new TaxTableModel();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(table);
        panel1 = new JPanel();
        panel1.add(scrollPane);
        c.add(panel1, BorderLayout.CENTER);

        for (Employee employee : employees) {

            model.addToTable(employee);
        }

        JPanel panel2 = new JPanel();
        label = new JLabel("Total Tax:");
        textField = new JTextField();
        panel2.add(label);
        panel2.add(textField);
        c.add(panel2, BorderLayout.NORTH);
        textField.setEditable(false);

        this.setVisible(true);
        this.setTitle("Tax Report");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
