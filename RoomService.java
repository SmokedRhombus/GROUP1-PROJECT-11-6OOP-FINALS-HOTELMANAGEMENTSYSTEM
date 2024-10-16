//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.


import java.util.Date;

public class RoomService {
    private static int nextId = 1;
    private int id;
    private Reservation reservation;
    private String item;
    private double price;
    private Date orderDate;
    private RoomServiceStatus status;

    public RoomService(Reservation reservation, String item, double price) {
        this.id = nextId++;
        this.reservation = reservation;
        this.item = item;
        this.price = price;
        this.orderDate = new Date();
        this.status = RoomServiceStatus.ORDERED;
    }

    public int getId() { return id; }
    public Reservation getReservation() { return reservation; }
    public String getItem() { return item; }
    public double getPrice() { return price; }
    public Date getOrderDate() { return orderDate; }
    public RoomServiceStatus getStatus() { return status; }

    public void setStatus(RoomServiceStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Room Service #" + id + ": " + item + " - PHP" + price + 
               " - Room " + reservation.getRoom().getRoomNumber() + 
               " - Status: " + status + " - Order Date: " + orderDate;
    }
}

enum RoomServiceStatus {
    ORDERED, PREPARING, DELIVERED, CANCELLED
}