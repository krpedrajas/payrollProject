import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

//maybe use payroll page table for history of wage payments?
//might not use table for payroll page anymore (not final do not delete)
public class TaxTableModel extends AbstractTableModel {

    ArrayList<Employee> employees;
    String [] columns = {"ID","Name" ,"Gross Income", "SSS", "Pag-Ibig", "Phil-Health", "Withholding Tax", "Total Deductions"};

    public TaxTableModel(){
        employees = new ArrayList<>();
    }

    public void addToTable(Employee e){
        employees.add(e);
        this.fireTableDataChanged();
    }

    public void deleteFromTable(int[] index){
        if(this.getRowCount()>1){
            for(int i=index.length-1;i>=0;i--){
                employees.remove(index[i]);
            }
        }else{
            employees.remove(index[0]);
        }
        this.fireTableDataChanged();
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
                return e.getEmployeeId();
            case 1:
                return e.getName();
            case 2:
                return e.getGrossPay();
            case 3:
                return e.getSSS();
            case 4:
                return e.getPagIbig();
            case 5:
                return e.getPhilHealth();
            case 6:
                return e.getWithHoldingTax();
            case 7:
                double totaldeduction = e.getSSS()+e.getPagIbig()+e.getPhilHealth()+e.getWithHoldingTax();
                return totaldeduction;
        }
        return null;
    }
}

