package application;

import api.HotelResource;
import application.AdminMenu;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainMenu {
    public static void main(String[] args) {
        actions();
    }

    public static void menu() {
        System.out.println("\nWelcome to the Hotel Reservation Application");
        System.out.println("---------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account \n4. Admin \n5. Exit");
        System.out.println("---------------------------");
        System.out.println("Please select a number for the menu option");
    }

    public static void actions(){
        menu();
        Scanner scanner = new Scanner(System.in);
        boolean runMainMenu = true;
        while(runMainMenu) {
            try {
                String userInput = scanner.nextLine();
                int userActions = Integer.parseInt(userInput);
                System.out.println("You Entered: " + userInput);
                switch (userActions) {
                    case 1:
                        findAndReserveARoom();
                        break;
                    case 2:
                        seeMyReservation();
                        break;
                    case 3:
                        createAnAccount();
                        break;
                    case 4:
                        adminMenu();
                        break;
                    case 5:
                        runMainMenu = false;
                        System.out.println("Exiting Application. See You Soon!");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
            catch (Exception ex) {
                System.out.println("Please select 1 - 5 from the menu option");
            }
            break;
        }
    }

    public static Date checkInDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date checkIn = null;
        boolean validCheckInDate = false;
        while(!validCheckInDate) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter CheckIn Date (mm/dd/yyyy):");
            String scanCheckIn = scanner.nextLine();

            try {
                checkIn = simpleDateFormat.parse(scanCheckIn);
                Date today = new Date();
                if (checkIn.before(today)) {
                    System.out.println("Please enter valid Check-In date");
                } else {
                    validCheckInDate = true;
                }
            } catch (ParseException e) {
                System.out.println("Please enter in correct format (mm/dd/yyyy)");
            }
        }
        return checkIn;
    }

    public static Date checkOutDate(Date checkInDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date checkOut = null;
        boolean validCheckOutDate = false;
        while(!validCheckOutDate) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter Check-Out Date (mm/dd/yyyy):");
            String scanCheckOut = scanner.nextLine();

            try {
                checkOut = simpleDateFormat.parse(scanCheckOut);
                if (checkOut.after(checkInDate)) {
                    validCheckOutDate = true;
                } else {
                    System.out.println("Please enter valid Check-Out date");
                }
            } catch (ParseException e) {
                System.out.println("Please enter in correct format (mm/dd/yyyy)");
            }
        }
        return checkOut;
    }

    public static void findAndReserveARoom(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to book a room? (y/n)");
        String bookRoom = scanner.next().toLowerCase();
        switch(bookRoom) {
            case"y":
                System.out.println("Do you have an account with us? (y/n):");
                String hasAccount = scanner.next().toLowerCase();
                switch (hasAccount) {
                    case "y":
                        Date checkInDate = checkInDate();
                        Date checkOutDate = checkOutDate(checkInDate);

                        Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);

                        if (availableRooms.isEmpty()) {
                            System.out.println("Currently no available room");
                            return;
                        } else {
                            System.out.println("Available room: " + availableRooms);

                        }
                    case "n":
                        System.out.println("Please create an account before booking.");
                        createAnAccount();
                        return;

                        //display reservation detail: name, room price, date
//                        System.out.println("Would you like to book a room? (y/n)");
//
//                        System.out.println("Do you have an account with us? (y/n):");
//                        //yes
//                        System.out.println("Enter Email: (format: name@domain.com)");
//                        System.out.println("What room would you like to reserve?");
                }
            case "n":
                System.out.println("Directing you back to the main menu.");
                actions();
        }
    }

    public static void seeMyReservation(){
        Scanner scanEmail = new Scanner(System.in);
        System.out.println("Please Enter Email: (format: name@domain.com)");
        String email = scanEmail.nextLine();
        Customer customer = HotelResource.getCustomer(email);
        try {
            if (Objects.isNull(customer)) {
                System.out.println("Customer has no account with us. Please create account.");
                actions();
            }
            Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);
            if (reservations.isEmpty()){
                System.out.println("Currently no reservation");
                actions();
            }
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
        catch(Exception IllegalArgumentException){
            System.out.println("Please use format (name@domain.com) and try again");
        }
    }

    public static void createAnAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("First name");
        String firstName = scanner.nextLine();
        System.out.println("Last name");
        String lastName = scanner.nextLine();
        boolean accountCreated = false;
        try {
            System.out.println("Please Enter Email: (format: name@domain.com)");
            String email = scanner.nextLine();
            HotelResource.createACustomer(email, firstName, lastName);
            accountCreated = true;
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAnAccount();
        }
        if (accountCreated){
            System.out.println("Account Created Successfully");
            actions();
        }
    }

    public static void adminMenu(){
        AdminMenu.actions();
    }

}

