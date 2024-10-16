//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

import java.util.Date;

public class Reservation {
    private static int nextId = 1;
    private int id;
    private Customer customer;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private ReservationStatus status;

    public Reservation(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        this.id = nextId++;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED;
    }

    public boolean overlaps(Date checkIn, Date checkOut) {
        return this.checkInDate.before(checkOut) && checkIn.before(this.checkOutDate);
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public ReservationStatus getStatus() { return status; }

    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation #" + id + ": " + customer.getName() + " - " + room + 
               " - Check-in: " + checkInDate + " - Check-out: " + checkOutDate + 
               " - Status: " + status;
    }
}

enum ReservationStatus {
    CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELLED
}