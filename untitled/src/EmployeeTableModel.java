import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EmployeeTableModel extends AbstractTableModel {

    ArrayList<Employee> employees;
    String[] columns ={"Name", "Position", "Salary"};


    public EmployeeTableModel(){
        employees = new ArrayList<>();
    }

    public void addToTable(Employee employee){
        employees.add(employee);
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

    public void editSelectedRow(int index, String firstName, String lastName, String position, String salary){
        if(!firstName.isEmpty()){
            employees.get(index).setFirstName(firstName);
        }
        if(!lastName.isEmpty()){
            employees.get(index).setLastName(lastName);
        }
        if(!position.isEmpty()){
            employees.get(index).setPosition(position);
        }
        if(!salary.isEmpty()){
            double s=Double.parseDouble(salary);
            employees.get(index).setSalary(s);
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
        Employee employee = employees.get(rowIndex);
        if(columnIndex==0){
            return employee.getFirstName()+" "+employee.getLastName();
        }else if(columnIndex==1){
            return employee.getPosition();
        }else{
            return employee.getSalary();
        }
    }
}
