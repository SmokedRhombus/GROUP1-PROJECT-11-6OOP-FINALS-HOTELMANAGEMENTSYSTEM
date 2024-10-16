//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

import java.util.Date;

public class Review {
    private static int nextId = 1;
    private int id;
    private Customer customer;
    private int rating;
    private String comment;
    private Date reviewDate;

    public Review(Customer customer, int rating, String comment) {
        this.id = nextId++;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = new Date();
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Date getReviewDate() { return reviewDate; }

    @Override
    public String toString() {
        return "Review #" + id + ": " + rating + "/5 stars - " + customer.getName() + 
               " - Date: " + reviewDate + "\nComment: " + comment;
    }
}