package application;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.Reservation;
import model.RoomType;

import java.util.Collection;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;

public class AdminMenu {

    public static void menu() {
        System.out.println("\nWelcome to the Admin Menu");
        System.out.println("---------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms \n3. See all reservations");
        System.out.println("4. Add a room \n5. Back to main menu");
        System.out.println("6. Add Test Data");
        System.out.println("---------------------------");
        System.out.println("Please select a number for the menu option");
    }


    public static void actions(){
        menu();
        Scanner scanner = new Scanner(System.in);
        boolean runAdminMenu = true;
        while(runAdminMenu) {
            try {
                String userInput = scanner.nextLine();
                int userActions = Integer.parseInt(userInput);
                System.out.println("You Entered: " + userInput);
                switch (userActions) {
                    case 1:
                        seeAllCustomers();
                        break;
                    case 2:
                        seeAllRooms();
                        break;
                    case 3:
                        seeAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        runAdminMenu = false;
                        System.out.println("Going back to main menu");
                        MainMenu.actions();
                        break;
                    case 6:
                        testData();
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
            catch (Exception ex) {
                System.out.println("Please select 1 - 6 from the menu option");
            }
        }
    }

    public static void seeAllCustomers() {
        Collection<Customer> allCustomers = AdminResource.getAllCustomers();
        if (allCustomers.isEmpty()){
            System.out.println("There are no customers in the system");
        } else{
            for (Customer customer : allCustomers){
                System.out.println(customer.toString());
            }
        }
    }

    public static void seeAllRooms(){
        Collection<IRoom> allRooms = AdminResource.getAllRooms();
        if (allRooms.isEmpty()){
            System.out.println("There are no rooms in the system");
        } else{
            for (IRoom room : allRooms){
                System.out.println(room.toString());
            }
        }
    }

    public static void seeAllReservations() {
        Collection<Reservation> allReservations = AdminResource.displayAllReservations();
        if (allReservations.isEmpty()){
            System.out.println("There are no reservations in the system");
        } else{
            for (Reservation reservation : allReservations){
                System.out.println(reservation.toString());
            }
        }
    }

//    public static void getRoomNumber(Scanner scanner){
//        String roomNumber = null;
//        boolean validNumber = false;
//        while(!validNumber) {
//            System.out.println("Enter Room Number");
//            String roomNumber = scanner.nextLine();
//            IRoom checkRoomNumber = HotelResource.getRoom(roomNumber);
//            if (checkRoomNumber == null){
//                validNumber = true;
//            }
//        }
//    }
//
//
//
//
    public static void addARoom() {
        System.out.println("Add room");
    }

    public static void testData() {
        Room newRoom1 = new Room("1", 200.0, RoomType.DOUBLE);
        Room newRoom2 = new Room("2", 100.0, RoomType.SINGLE);
        AdminResource.addRoom(newRoom1);
        AdminResource.addRoom(newRoom2);

        HotelResource.createACustomer("a@email.com", "Fn1", "Ln1");
        HotelResource.createACustomer("b@email.com", "Fn2", "Ln2");
        HotelResource.createACustomer("c@email.com", "Fn3", "Ln3");

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        Date checkInDate = null;
        Date checkOutDate = null;

        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        checkInDate = calendar.getTime();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 10);
        checkOutDate = calendar.getTime();
        HotelResource.bookARoom("a@email.com", HotelResource.getRoom("1"), checkInDate, checkOutDate);

        calendar.setTime(today);
        calendar.add(Calendar.DATE, 10);
        checkInDate = calendar.getTime();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 1);
        checkOutDate = calendar.getTime();
        HotelResource.bookARoom("b@email.com", HotelResource.getRoom("2"), checkInDate, checkOutDate);

        calendar.setTime(today);
        calendar.add(Calendar.DATE, 3);
        checkInDate = calendar.getTime();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 4);
        checkOutDate = calendar.getTime();
        HotelResource.bookARoom("c@email.com", HotelResource.getRoom("1"), checkInDate, checkOutDate);

        System.out.println("Test data added");
    }
}
