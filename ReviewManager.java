//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

public class ReviewManager {
    private Hotel hotel;

    public ReviewManager(Hotel hotel) {
        this.hotel = hotel;
    }

    public void displayAllReviews() {
        List<Review> reviews = hotel.getReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            System.out.println("All Reviews:");
            reviews.forEach(System.out::println);
        }
    }

    public void displayReviewsByRating(int rating) {
        List<Review> filteredReviews = hotel.getReviews().stream()
                .filter(review -> review.getRating() == rating)
                .collect(Collectors.toList());

        if (filteredReviews.isEmpty()) {
            System.out.println("No reviews found with rating " + rating);
        } else {
            System.out.println("Reviews with rating " + rating + ":");
            filteredReviews.forEach(System.out::println);
        }
    }

    public void displayAverageRating() {
        List<Review> reviews = hotel.getReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews available to calculate average rating.");
        } else {
            double averageRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            System.out.printf("Average Rating: %.2f stars\n", averageRating);
        }
    }

    public void displayRecentReviews(int count) {
        List<Review> recentReviews = hotel.getReviews().stream()
                .sorted((r1, r2) -> r2.getReviewDate().compareTo(r1.getReviewDate()))
                .limit(count)
                .collect(Collectors.toList());

        if (recentReviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            System.out.println("Most Recent Reviews:");
            recentReviews.forEach(System.out::println);
        }
    }

    public void displayReviewsBetweenDates(Date startDate, Date endDate) {
        List<Review> filteredReviews = hotel.getReviews().stream()
                .filter(review -> review.getReviewDate().after(startDate) && 
                                  review.getReviewDate().before(endDate))
                .collect(Collectors.toList());

        if (filteredReviews.isEmpty()) {
            System.out.println("No reviews found between the specified dates.");
        } else {
            System.out.println("Reviews between " + startDate + " and " + endDate + ":");
            filteredReviews.forEach(System.out::println);
        }
    }
}