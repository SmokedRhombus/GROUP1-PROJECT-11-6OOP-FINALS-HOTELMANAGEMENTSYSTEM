//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.


import java.util.*;
import java.util.stream.Collectors;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Payment> payments;
    private List<Review> reviews;
    private List<RoomService> roomServices;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.roomServices = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        // Add some sample rooms
        rooms.add(new StandardRoom(101, 100.0));
        rooms.add(new DoubleRoom(201, 150.0));
        rooms.add(new DeluxeRoom(301, 200.0));
        rooms.add(new SuiteRoom(401, 300.0));
        rooms.add(new PresidentialSuite(501, 500.0));
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addRoomService(RoomService roomService) {
        roomServices.add(roomService);
    }

    public List<Room> searchAvailableRooms(Date checkIn, Date checkOut, String roomType) {
        return rooms.stream()
            .filter(room -> room.getClass().getSimpleName().equalsIgnoreCase(roomType))
            .filter(room -> isRoomAvailable(room, checkIn, checkOut))
            .collect(Collectors.toList());
    }

    public List<Room> getAvailableRooms(Date checkIn, Date checkOut) {
        return rooms.stream()
            .filter(room -> isRoomAvailable(room, checkIn, checkOut))
            .collect(Collectors.toList());
    }

    public boolean cancelReservation(int reservationId) {
        Reservation reservation = reservations.stream()
            .filter(r -> r.getId() == reservationId)
            .findFirst()
            .orElse(null);
        if (reservation != null && reservation.getStatus() == ReservationStatus.CONFIRMED) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            return true;
        }
        return false;
    }

    private boolean isRoomAvailable(Room room, Date checkIn, Date checkOut) {
        return reservations.stream()
            .filter(res -> res.getRoom().equals(room))
            .noneMatch(res -> res.overlaps(checkIn, checkOut));
    }

    public void generateRevenueReport() {
        double totalRevenue = payments.stream().mapToDouble(Payment::getAmount).sum();
        System.out.println("Total Revenue: PHP" + totalRevenue);
        Map<String, Double> revenueByRoomType = new HashMap<>();
        for (Payment payment : payments) {
            String roomType = payment.getReservation().getRoom().getClass().getSimpleName();
            revenueByRoomType.put(roomType, revenueByRoomType.getOrDefault(roomType, 0.0) + payment.getAmount());
        }
        System.out.println("Revenue by Room Type:");
        for (Map.Entry<String, Double> entry : revenueByRoomType.entrySet()) {
            System.out.println(entry.getKey() + ": PHP" + entry.getValue());
        }
    }

    // Getters
    public String getName() { return name; }
    public List<Room> getRooms() { return rooms; }
    public List<Reservation> getReservations() { return reservations; }
    public List<Customer> getCustomers() { return customers; }
    public List<Employee> getEmployees() { return employees; }
    public List<Payment> getPayments() { return payments; }
    public List<Review> getReviews() { return reviews; }
    public List<RoomService> getRoomServices() { return roomServices; }
}