//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

public abstract class Room {
    protected int roomNumber;
    protected double price;

    public Room(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public int getRoomNumber() { return roomNumber; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " - Room " + roomNumber + " (PHP" + price + " per night)";
    }
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class PresidentialSuite extends Room {
    public PresidentialSuite(int roomNumber, double price) {
        super(roomNumber, price);
    }
}