//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

import java.util.Date;

public class Payment {
    private static int nextId = 1;
    private int id;
    private Reservation reservation;
    private double amount;
    private Date paymentDate;
    private PaymentMethod paymentMethod;

    public Payment(Reservation reservation, double amount, PaymentMethod paymentMethod) {
        this.id = nextId++;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentDate = new Date();
        this.paymentMethod = paymentMethod;
    }

    public int getId() { return id; }
    public Reservation getReservation() { return reservation; }
    public double getAmount() { return amount; }
    public Date getPaymentDate() { return paymentDate; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    @Override
    public String toString() {
        return "Payment #" + id + ": PHP" + amount + " - " + paymentMethod + 
               " - Date: " + paymentDate + " - Reservation #" + reservation.getId();
    }
}

enum PaymentMethod {
    CASH, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER
}