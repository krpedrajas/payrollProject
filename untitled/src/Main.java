import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    public static void main(String[] args) {
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(new Employee("1", "Kenneth Pedrajas", "DJ", 500));
        employees.get(0).setHoursAttended(55.604);
        employees.add(new Employee("2", "Ruan Justiniani", "Software Engineer", 350));
        employees.add(new Employee("3", "Jujin Ferrer", "Software Developer", 350));

        MainUi mainUi = new MainUi(employees);
    }
}