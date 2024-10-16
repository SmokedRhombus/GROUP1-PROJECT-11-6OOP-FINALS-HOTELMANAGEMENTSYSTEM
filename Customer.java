//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

public class Customer {
    private static int nextId = 1;
    private int id;
    private String name;
    private String email;
    private String phone;

    public Customer(String name, String email) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Customer #" + id + ": " + name + " (" + email + ")";
    }
}