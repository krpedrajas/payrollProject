public class Employee {

    // **Employee Management**
    //
    //   - Add, update, and delete employee records.
    //   - Store personal information, employment details, and salary information.
    //   - Track employee attendance and leaves.

    private String firstName, lastName, position;
    private double hourlyRate;
    private int hoursAttended;

    public Employee(String firstName, String lastName, String position, double salary){
        this.firstName=firstName;
        this.lastName=lastName;
        this.position=position;
        this.hourlyRate =salary;
    }

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

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursAttended() {
        return hoursAttended;
    }

    public void setHoursAttended(int hoursAttended) {
        this.hoursAttended = hoursAttended;
    }
}
