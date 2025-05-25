package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EmployeeTableModel extends AbstractTableModel {

    ArrayList<Employee> employees;
    String[] columns ={"Employee ID", "Name", "Position", "Salary"};


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

    public void editSelectedRow(int index, String employeeId, String name, String position, String salary){
        if(!employeeId.isEmpty()){
            employees.get(index).setEmployeeId(employeeId);
        }
        if(!name.isEmpty()){
            employees.get(index).setName(name);
        }
        if(!position.isEmpty()){
            employees.get(index).setPosition(position);
        }
        if(!salary.isEmpty()){
            double s=Double.parseDouble(salary);
            employees.get(index).setHourlyRate(s);
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
            return employee.getEmployeeId();
        }else if(columnIndex==1) {
            return employee.getName();
        }else if(columnIndex==2){
            return employee.getPosition();
        }else{
            return employee.getHourlyRate();
        }
    }
    public Employee getEmployeeAt(int rowIndex) {
        return employees.get(rowIndex);
    }

}
