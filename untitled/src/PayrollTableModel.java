import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PayrollTableModel extends AbstractTableModel {

    ArrayList<Employee> employees;
    String [] columns = {"Name", "Position", "Gross Salary", "Net Salary", "Total Deductions", "Work Hours"};

    public PayrollTableModel (){
        employees = new ArrayList<>();
    }

    

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column){

        return columns[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee e = employees.get(rowIndex);

        switch (columnIndex){
            case 0:
                return e.getFirstName() + " " + e.getLastName();
            case 1:
                return e.getPosition();
            case 2:
                return e.getGrossPay();
            case 3:
                return e.getNetPay();
            case 4:
                return e.getDeductions();
            case 5:
                return e.getHoursAttended();
        }
        return null;
    }
}
