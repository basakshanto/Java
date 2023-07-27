package com.ronvolt.airport.interfaces;
import java.util.Scanner;
public class Airline {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Booking booking = new Booking();

        booking.greetings();

        while (true) {
            try {
                booking.startProcess();
            } catch (Exception e) {
                System.out.println("Something bad happened with the engine, MAYDAY! MAYDAY! Abort the mission!");
            }

            try {
                System.out.print("Do you wish to continue? Yes/No : ");
                String choice = scanner.nextLine();

                if ((choice.toLowerCase()).equals("yes") || (choice.toLowerCase()).equals("no")) {
                    if ((choice.toLowerCase()).equals("yes")) {
                        continue;
                    } else {
                        System.out.println("Thank you for using Ronvolt Air Service!");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }

        scanner.close();
    }
}
