public class Employee {

    // **Employee Management**
    //
    //   - Add, update, and delete employee records.
    //   - Store personal information, employment details, and salary information.
    //   - Track employee attendance and leaves.

    private String firstName, lastName, position;
    private double salary;
    private int hoursAttended;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHoursAttended() {
        return hoursAttended;
    }

    public void setHoursAttended(int hoursAttended) {
        this.hoursAttended = hoursAttended;
    }
}
