import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PagIbigUI extends JFrame {
    JTable table;
    JScrollPane scrollPane;
    JPanel panel1;
    Container c;
    PagibigTableModel model;
    JLabel label;
    JTextField textField;

    public PagIbigUI(ArrayList<Employee>employees){
        c = this.getContentPane();
        model=new PagibigTableModel();
        table=new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane=new JScrollPane(table);
        panel1= new JPanel();
        panel1.add(scrollPane);
        c.add(panel1, BorderLayout.CENTER);

        JPanel panel2= new JPanel();
        label = new JLabel("Total Pag-Ibig:");
        textField = new JTextField();
        panel2.add(label);
        panel2.add(textField);
        c.add(panel2, BorderLayout.NORTH);

        double total = 0;

        for (Employee employee: employees){
            total+=employee.getPagIbig();
            model.addToTable(employee);
        }
        textField.setText(String.valueOf(total));
        textField.setEditable(false);

        JButton backB=new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(backB);
        c.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setTitle("Pag-Ibig Report");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagIbigUI.this.dispose();
            }
        });
    }
}