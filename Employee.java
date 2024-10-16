//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

public class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.id = nextId++;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }

    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee #" + id + ": " + name + " - " + position + " (Salary: PHP" + salary + ")";
    }
}