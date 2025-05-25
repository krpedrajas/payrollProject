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
                return Math.round(e.getGrossPay()*100.0)/100.0;
            case 3:
                return Math.round(e.getSSS()*100.0)/100.0;
            case 4:
                return Math.round(e.getPagIbig()*100.0)/100.0;
            case 5:
                return Math.round(e.getPhilHealth()*100.0)/100.0;
            case 6:
                return Math.round(e.getWithHoldingTax()*100.0)/100.0;
            case 7:
                double totaldeduction = e.getSSS()+e.getPagIbig()+e.getPhilHealth()+e.getWithHoldingTax();
                return Math.round(totaldeduction*100.0)/100.0;
        }
        return null;
    }
}

