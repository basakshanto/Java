package com.ronvolt.airport.interfaces;

import com.ronvolt.airport.interfaces.Reservation;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Booking implements Reservation {
    Scanner scanner = new Scanner(System.in);

    private boolean[] businessSeats = new boolean[10];
    private boolean[] economySeats = new boolean[10];
    private int type;
    private int seatNumber;
    private String name;
    private String phone;

    public void greetings() {
        System.out.println("Welcome to Ronvolt Air Service!");
    }

    public void startProcess() {
        this.setBookingTypes();
    }

    public void setBookingTypes() {
        System.out.println("\n1. Business Class\t2. Economy Class");

        while (true) {
            try {
                System.out.print("Please select your desired option : ");
                this.type = scanner.nextInt();
                scanner.nextLine();

                if (this.type == 1 || this.type == 2) {
                    this.promptChoice();
                    break;
                } else {
                    System.out.println("Invalid choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    public void promptChoice() {
        while (true) {
            try {
                System.out.println("\nWould you like to select your seat of choice?");

                System.out.print("Type your choice [yes/no] : ");
                String choice = scanner.nextLine();

                if ((choice.toLowerCase()).equals("yes") || (choice.toLowerCase()).equals("no")) {
                    if ((choice.toLowerCase()).equals("yes")) {
                        this.showAvailableSeats();

                        this.assignSelectedSeat();
                    } else {
                        this.assignRandomSeat();
                    }
                    break;
                } else {
                    System.out.println("Invalid choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    public void showAvailableSeats() {
        String showSeats = "";
        if (this.type == 1) {
            System.out.println("Business class seats : ");
            for (int i = 0; i < this.businessSeats.length; i++) {
                showSeats += this.businessSeats[i] ? (i + 1) + ". Booked " : (i + 1) + ". Free ";
                showSeats += i == 4 ? "\n" : "";
            }
        } else if (this.type == 2) {
            System.out.println("Economy class seats : ");
            for (int i = 0; i < this.economySeats.length; i++) {
                showSeats += this.economySeats[i] ? (i + 1) + ". Booked " : (i + 1) + ". Free ";
                showSeats += i == 4 ? "\n" : "";
            }
        }

        System.out.println(showSeats);
    }

    public boolean checkSeatAvailability() {
        boolean availability = false;

        for (int i = 0; i < this.businessSeats.length; i++) {
            if (this.type == 1) {
                if (!this.businessSeats[i]) {
                    availability = true;
                    break;
                }
            } else if (this.type == 2) {
                if (!this.economySeats[i]) {
                    availability = true;
                    break;
                }
            }
        }

        return availability;
    }

    public void assignRandomSeat() {
        while (true) {
            if (!this.checkSeatAvailability()) {
                System.out.println("No seat available! Try different class?");
                break;
            }

            int randInt = (int) Math.floor(Math.random() * 10);

            for (int i = 0; i < this.businessSeats.length; i++) {
                if (this.type == 1) {
                    if (!this.businessSeats[randInt]) {
                        break;
                    } else {
                        randInt = (int) Math.floor(Math.random() * 10);
                    }
                } else if (this.type == 2) {
                    if (!this.economySeats[randInt]) {
                        break;
                    } else {
                        randInt = (int) Math.floor(Math.random() * 10);
                    }
                }
            }

            try {
                this.seatNumber = randInt;
                if (this.type == 1) {
                    if (!this.businessSeats[this.seatNumber]) {
                        this.businessSeats[this.seatNumber] = true;
                        System.out.println("Your seat number is : " + (this.seatNumber + 1));

                        this.showPrice();
                        break;
                    } else {
                        System.out.println("Seat is ocuppied, select another!");
                    }
                } else {
                    if (!this.economySeats[this.seatNumber]) {
                        this.economySeats[this.seatNumber] = true;
                        System.out.println("Your seat number is : " + (this.seatNumber + 1));

                        this.showPrice();

                        break;
                    } else {
                        System.out.println("Seat is ocuppied, select another!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    public void assignSelectedSeat() {
        while (true) {
            if (!this.checkSeatAvailability()) {
                System.out.println("No seat available! Try different class?");
                break;
            }

            try {
                System.out.print("\nPlease select your desired seat number : ");
                this.seatNumber = scanner.nextInt() - 1;
                scanner.nextLine();
                if (this.type == 1) {
                    if (!this.businessSeats[this.seatNumber]) {
                        this.businessSeats[this.seatNumber] = true;
                        System.out.println("Your seat number is : " + (this.seatNumber + 1));

                        this.showPrice();
                        break;
                    } else {
                        System.out.println("Seat is ocuppied, select another!");
                    }
                } else {
                    if (!this.economySeats[this.seatNumber]) {
                        this.economySeats[this.seatNumber] = true;
                        System.out.println("Your seat number is : " + (this.seatNumber + 1));

                        this.showPrice();

                        break;
                    } else {
                        System.out.println("Seat is ocuppied, select another!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    public void showPrice() {
        if (this.type == 1) {
            System.out.println("Price for the ticket will be : $120.00");
        } else {
            System.out.println("Price for the ticket will be : $90.00");
        }

        this.getPersonalInfo();

        this.bookingConfirmation();
    }

    void getPersonalInfo() {
        System.out.print("Enter your name : ");
        this.name = scanner.nextLine();

        System.out.print("Enter your phone number : ");
        this.phone = scanner.nextLine();
    };

    public void bookingConfirmation() {
        while (true) {
            try {
                System.out.print("Type \"CONFIRM\" or \"CANCEL\" to proceed : ");
                String choice = scanner.nextLine();

                if (choice.equals("CONFIRM") || choice.equals("CANCEL")) {
                    if (choice.equals("CONFIRM")) {
                        System.out.println("Your seat has been successfully Booked!");
                        this.generateTicket();
                    } else if (choice.equals("CANCEL")) {
                        System.out.println("Your booking has been cancelled!");
                    }

                    break;
                } else {
                    System.out.println("Invalid choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }

    String ticketDetails() {
        String ticket_type = this.type == 1 ? "Business" : "Economy";

        String ticket_price = this.type == 1 ? "$120.00" : "$90.00";

        return "Name : " + this.name + "\n" + "Phone : " + this.phone + "\n" + "Ticket Type : " + ticket_type + "\n"
                + "Seat Number : " + (this.seatNumber + 1) + "\n" + "Price : " + ticket_price + "\n"
                + "\n-----------------------------------------\n" + "Have a safe journey, Ronvolt Air Service!";
    }

    public void generateTicket() {
        String file_name = this.type == 1 ? "business_" + (this.seatNumber + 1) : "economy_" + (this.seatNumber + 1);

        File file = new File("tickets/" + file_name + ".txt");

        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);

            writer.write(this.ticketDetails());
        } catch (IOException io) {
            System.out.println("Unfortunately, you do not have enough permission, aborting!");
        } finally {
            try {
                writer.close();

                while (true) {
                    try {
                        System.out.print("Do you want to see your ticket? Yes/No : ");
                        String choice = scanner.nextLine();

                        if ((choice.toLowerCase()).equals("yes") || (choice.toLowerCase()).equals("no")) {
                            if ((choice.toLowerCase()).equals("yes")) {
                                System.out.println("typed yes in ticket");
                                this.showTicket();
                            } else {
                                break;
                            }
                            break;
                        } else {
                            System.out.println("Invalid choice, please try again!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid choice, please try again!");
                    }
                }
            } catch (IOException io1) {
                System.out.println("Can not close the resource, aborting!");
            }
        }
    }

    public void showTicket() {
        System.out.println("Here in show ticket");
        String file_name = this.type == 1 ? "business_" + (this.seatNumber + 1) : "economy_" + (this.seatNumber + 1);

        File file = new File("tickets/" + file_name + ".txt");
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line = "";
            System.out.println("\n-----------------------------------------\n");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException io) {
            System.out.println("Unfortunately, you do not have enough permission, aborting!");
        } finally {
            try {
                fr.close();
            } catch (IOException io1) {
                System.out.println("Can not close the resource, aborting!");
            }
        }
    }
}
