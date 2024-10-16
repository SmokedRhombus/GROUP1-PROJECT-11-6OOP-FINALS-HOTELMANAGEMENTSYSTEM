# Hotel Management System

## Project Description

The Hotel Management System is a comprehensive Java-based application designed to streamline and automate various operations within a hotel environment. This system aims to enhance the efficiency of hotel management by providing a user-friendly interface for handling reservations, check-ins, check-outs, room services, and customer reviews.

## Features

- Make and cancel reservations
- Search for available rooms
- Manage check-ins and check-outs
- Order room service
- Handle customer reviews
- Generate revenue reports

## Scope and Limitations

### What it does:
- Provides essential functionalities for day-to-day hotel operations
- Implements core features using object-oriented programming principles

### What it doesn't do:
- No integration with external payment gateways
- No graphical user interface (console-based application)
- Not a networked multi-user system
- No employee management or inventory control
- No integration with external booking platforms

## Code-base Structure

The system follows an object-oriented design pattern with the following main components:

1. UserInterface: Main entry point for the application
2. Hotel: Core entity managing rooms, reservations, and customers
3. Room: Represents individual hotel rooms
4. Customer: Manages customer information
5. Reservation: Handles room reservations
6. RoomService: Manages room service orders
7. Review: Handles customer reviews and ratings
8. ReviewManager: Manages the collection and display of reviews
9. Payment: Handles payment transactions

## Getting Started

To get started with the Hotel Management System, follow these steps:

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Git (for cloning the repository)

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/hotel-management-system.git
   ```

2. Navigate to the project directory:
   ```
   cd hotel-management-system
   ```

3. Compile the Java files:
   ```
   javac *.java
   ```

### Running the Application

1. Run the main class:
   ```
   java UserInterface
   ```

2. Follow the on-screen prompts to interact with the system.

### Running Tests

To run the unit tests for the project:

1. Ensure you have JUnit 5 in your classpath.
2. Run the test suite:
   ```
   java org.junit.platform.console.ConsoleLauncher --scan-classpath
   ```

## Usage

The system provides a console-based menu for users to interact with various functionalities. Here's a basic overview of the main menu:

1. Make a reservation
2. Cancel a reservation
3. Search available rooms
4. Check-in
5. Check-out
6. Room service
7. Add review
8. View reviews
9. Generate revenue report
10. Exit

## Contributing

We welcome contributions to the Hotel Management System! If you'd like to contribute, please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

Please make sure to update tests as appropriate and adhere to the project's coding standards.

## License

This project is licensed under the MIT License - see the [MIT License](https://opensource.org/licenses/MIT) for details.

## Contact

Miguel Roa - meroa@student.hau.edu.ph
Alyssa Timbol - altimbol1@student.hau.edu.ph
Lance Apostol - lbapostol@student.hau.edu.ph
