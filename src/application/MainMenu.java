package application;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainMenu {
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

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
        try {
            String userInput = scanner.nextLine();
            int userActions = Integer.parseInt(userInput);
            System.out.println("You Entered: " + userInput);
            switch (userActions) {
                case 1:
                    findAndReserveARoom();
                    actions();
                case 2:
                    seeMyReservation();
                    actions();
                case 3:
                    createAnAccount();
                    actions();
                case 4:
                    AdminMenu.actions();
                    break;
                case 5:
                    System.out.println("Exiting Application. See You Soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
            }
        }
        catch (Exception ex) {
            System.out.println("Please select 1 - 5 from the menu option");
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


    public static String askForEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Email: (format: name@domain.com)");
        String email = scanner.next();
        try {
            Customer customer = HotelResource.getCustomer(email);
            if (Objects.isNull(customer)) {
                System.out.println("Customer has no account with us. Please create account.");
                createAnAccount();
            }
        }
        catch(Exception IllegalArgumentException){
            System.out.println("Please use format (name@domain.com) and try again");
            askForEmail();
        } return email;
    }

    public static String bookRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book a room? (y/n)");
        String bookRoom = scanner.next().toLowerCase();
        String email = null;
        if (bookRoom.equals("y")) {
            email = askForEmail();
        } else if (bookRoom.equals("n")) {
            System.out.println("Directing back to main menu");
            actions();
        } else {
            System.out.println("Please enter y for Yes or N for no");
            bookRoom();
        }return email;
    }

    public static IRoom pickRoom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What room would you like to reserve?");
        String roomNumber = scanner.next();
        return HotelResource.getRoom(roomNumber);
    }

    public static void alternativeDate(Collection<IRoom> availableRooms){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to check other date? (y/n)");
        String otherDate = scanner.next().toLowerCase();
        switch (otherDate) {
            case "y":
                Date checkInDate = checkInDate();
                Date checkOutDate = checkOutDate(checkInDate);
                reserveRoom(availableRooms, checkInDate, checkOutDate);
            case "n":
                System.out.println("Directing back to main menu");
                actions();
                break;
        }
    }

    public static void reserveRoom(Collection<IRoom> availableRooms, Date checkInDate, Date checkOutDate){
        if (availableRooms.isEmpty()){
            System.out.println("Currently no available room");
            alternativeDate(availableRooms);
        } else {
            System.out.println("Available room: ");
            availableRooms.forEach(System.out::println);

            String email = bookRoom();
            IRoom pickedRoom = pickRoom();

            if (availableRooms.contains(pickedRoom)){
                Reservation reservation = HotelResource.bookARoom(email,pickedRoom,checkInDate,checkOutDate);
                System.out.println(reservation);
                System.out.println("Room is reserved");
            } else {
                System.out.println("Unfortunately" + pickedRoom + " is reserved.");
                alternativeDate(availableRooms);
            }

        }
    }

    public static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have an account with us? (y/n):");
        String hasAccount = scanner.next().toLowerCase();
        if (hasAccount.equals("y")){

            Date checkInDate = checkInDate();
            Date checkOutDate = checkOutDate(checkInDate);

            Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);
            reserveRoom(availableRooms,checkInDate,checkOutDate);
        } else if (hasAccount.equals("n")){
            System.out.println("Please create an account before booking.");
            createAnAccount();
        } else {
            System.out.println("Please enter y for Yes or N for no");
            findAndReserveARoom();
        }
    }

    public static void seeMyReservation(){
        try {
            Scanner scanEmail = new Scanner(System.in);
            System.out.println("Please Enter Email: (format: name@domain.com)");
            String email = scanEmail.nextLine();
            Customer customer = HotelResource.getCustomer(email);

            if (Objects.isNull(customer)) {
                System.out.println("Customer has no account with us. Please create account.");
                actions();
            }else{
                Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);

                if (reservations.isEmpty()) {
                    System.out.println("Currently no reservation");
                    actions();
                } else {
                    for (Reservation reservation : reservations) {
                        System.out.println(reservation.toString());
                    }
                }
            }
        }
        catch(Exception IllegalArgumentException){
            System.out.println("Please use format (name@domain.com) and try again");
            seeMyReservation();
        }
    }

    public static void createAnAccount(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nFirst name");
            String firstName = scanner.nextLine();
            System.out.println("Last name");
            String lastName = scanner.nextLine();
            System.out.println("Please Enter Email: (format: name@domain.com)");
            String email = scanner.nextLine();
            HotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account Created Successfully");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAnAccount();
        }
    }
}

