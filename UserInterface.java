import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserInterface {
    private Scanner scanner;
    private Hotel hotel;
    private ReviewManager reviewManager;

    public UserInterface(Hotel hotel) {
        this.scanner = new Scanner(System.in);
        this.hotel = hotel;
        this.reviewManager = new ReviewManager(hotel);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Search available rooms");
            System.out.println("4. Check-in");
            System.out.println("5. Check-out");
            System.out.println("6. Room service");
            System.out.println("7. Add review");
            System.out.println("8. View reviews");
            System.out.println("9. Generate revenue report");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    searchAvailableRooms();
                    break;
                case 4:
                    checkIn();
                    break;
                case 5:
                    checkOut();
                    break;
                case 6:
                    orderRoomService();
                    break;
                case 7:
                    addReview();
                    break;
                case 8:
                    viewReviews();
                    break;
                case 9:
                    hotel.generateRevenueReport();
                    break;
                case 10:
                    System.out.println("Thank you for using the Hotel Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean cancelled = hotel.cancelReservation(reservationId);
        if (cancelled) {
            System.out.println("Reservation #" + reservationId + " has been cancelled successfully.");
        } else {
            System.out.println("Unable to cancel reservation. It may not exist or is not in a cancellable state.");
        }
    }

    private void searchAvailableRooms() {
        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        Date checkIn = parseDate(scanner.nextLine());
        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        Date checkOut = parseDate(scanner.nextLine());
        System.out.print("Enter room type (e.g., StandardRoom, DoubleRoom, DeluxeRoom, SuiteRoom, PresidentialSuite): ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = hotel.searchAvailableRooms(checkIn, checkOut, roomType);

        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms found for the specified criteria.");
        } else {
            System.out.println("Available " + roomType + "s:");
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private void makeReservation() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer(name, email);
        hotel.addCustomer(customer);

        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        Date checkIn = parseDate(scanner.nextLine());
        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        Date checkOut = parseDate(scanner.nextLine());

        System.out.print("Enter room type (StandardRoom, DoubleRoom, DeluxeRoom, SuiteRoom, PresidentialSuite): ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = hotel.searchAvailableRooms(checkIn, checkOut, roomType);
        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, no rooms of type " + roomType + " available for the selected dates.");
            return;
        }

        System.out.println("Available rooms:");
        for (int i = 0; i < availableRooms.size(); i++) {
            System.out.println((i + 1) + ". " + availableRooms.get(i));
        }

        System.out.print("Select a room (1-" + availableRooms.size() + "): ");
        int roomChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = availableRooms.get(roomChoice - 1);
        Reservation reservation = new Reservation(customer, selectedRoom, checkIn, checkOut);
        hotel.addReservation(reservation);

        System.out.println("Reservation made successfully!");
    }

    private void checkIn() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservation = hotel.getReservations().stream()
                .filter(r -> r.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        if (reservation.getStatus() == ReservationStatus.CHECKED_IN) {
            System.out.println("This reservation is already checked in.");
            return;
        }

        reservation.setStatus(ReservationStatus.CHECKED_IN);
        System.out.println("Check-in successful for reservation #" + reservationId);
    }

    private void checkOut() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservation = hotel.getReservations().stream()
                .filter(r -> r.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        if (reservation.getStatus() != ReservationStatus.CHECKED_IN) {
            System.out.println("This reservation is not checked in.");
            return;
        }

        // Calculate total cost
        long days = (reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24);
        double roomCost = reservation.getRoom().getPrice() * days;

        // Add room service costs
        double roomServiceCost = hotel.getRoomServices().stream()
                .filter(rs -> rs.getReservation().getId() == reservationId)
                .mapToDouble(RoomService::getPrice)
                .sum();

        double totalCost = roomCost + roomServiceCost;

        System.out.println("Total cost: PHP" + totalCost);
        System.out.print("Enter payment amount: ");
        double paymentAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (paymentAmount < totalCost) {
            System.out.println("Insufficient payment. Check-out cancelled.");
            return;
        }

        reservation.setStatus(ReservationStatus.CHECKED_OUT);
        Payment payment = new Payment(reservation, paymentAmount, PaymentMethod.CASH);
        hotel.addPayment(payment);

        System.out.println("Check-out successful for reservation #" + reservationId);
        System.out.println("Change: PHP" + (paymentAmount - totalCost));
    }

    private void orderRoomService() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservation = hotel.getReservations().stream()
                .filter(r -> r.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        if (reservation.getStatus() != ReservationStatus.CHECKED_IN) {
            System.out.println("This reservation is not checked in.");
            return;
        }

        System.out.print("Enter item name: ");
        String item = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        RoomService roomService = new RoomService(reservation, item, price);
        hotel.addRoomService(roomService);

        System.out.println("Room service order placed successfully!");
    }

    private void addReview() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        Customer customer = hotel.getCustomers().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Customer not found. Creating a new customer record.");
            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();
            customer = new Customer(name, email);
            hotel.addCustomer(customer);
        }

        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter review comment: ");
        String comment = scanner.nextLine();

        Review review = new Review(customer, rating, comment);
        hotel.addReview(review);

        System.out.println("Review added successfully!");
    }

    private void viewReviews() {
        while (true) {
            System.out.println("\n--- Review Management ---");
            System.out.println("1. View all reviews");
            System.out.println("2. View reviews by rating");
            System.out.println("3. View average rating");
            System.out.println("4. View recent reviews");
            System.out.println("5. View reviews between dates");
            System.out.println("6. Return to main menu");
            System.out.print("Enter your choice.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    reviewManager.displayAllReviews();
                    break;
                case 2:
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    reviewManager.displayReviewsByRating(rating);
                    break;
                case 3:
                    reviewManager.displayAverageRating();
                    break;
                case 4:
                    System.out.print("Enter number of recent reviews to display: ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    reviewManager.displayRecentReviews(count);
                    break;
                case 5:
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    Date startDate = parseDate(scanner.nextLine());
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    Date endDate = parseDate(scanner.nextLine());
                    reviewManager.displayReviewsBetweenDates(startDate, endDate);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}