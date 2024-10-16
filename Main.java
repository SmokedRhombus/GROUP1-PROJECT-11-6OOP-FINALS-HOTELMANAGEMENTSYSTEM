//GROUP 1: PROJECT 11 6OOP | CYB-201
//MEMBERS:
// ROA, MIGUEL DOMINIC E.
// GONZALES, IAN MANUEL P.
// TIMBOL, ALYSSA LOUISE L.
// APOSTOL, LANCE JEZREEL B.

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Oasis");
        UserInterface ui = new UserInterface(hotel);
        ui.start();
    }
}