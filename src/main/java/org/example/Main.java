package org.example;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
//        ArrayList<Employee> employees=new ArrayList<>();
//        employees.add(new Employee("1", "Kenneth Pedrajas", "DJ", 500));
//        employees.get(0).setHoursAttended(55.604);
//        employees.add(new Employee("2", "Ruan Justiniani", "Software Engineer", 350));
//        employees.get(1).setHoursAttended(45.45);
//        employees.add(new Employee("3", "Jujin Ferrer", "Software Developer", 350));
//        employees.get(2).setHoursAttended(89.09);

        FirestoreConnection connection = new FirestoreConnection();
        ArrayList<Employee>employees=connection.getAllEmployees();

        MainUi mainUi = new MainUi(employees);
    }
}