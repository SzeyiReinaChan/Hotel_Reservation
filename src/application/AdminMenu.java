package application;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {

    public static void menu() {
        System.out.println("\nWelcome to the Admin Menu");
        System.out.println("---------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms \n3. See all reservations");
        System.out.println("4. Add a room \n5. Back to main menu");
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
                    default:
                        System.out.println("Invalid Input");
                }
            }
            catch (Exception ex) {
                System.out.println("Please select 1 - 5 from the menu option");
            }
        }
    }

    public static void seeAllCustomers() {
        Collection<Customer> allCustomers = AdminResource.adminResource.getAllCustomers();
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
        Collection<Reservation> allReservations = AdminResource.adminResource.displayAllReservations();
        if (allReservations.isEmpty()){
            System.out.println("There are no reservations in the system");
        } else{
            for (Reservation reservation : allReservations){
                System.out.println(reservation.toString());
            }
        }
    }

    public static void addARoom() {
        System.out.println("adding a room");
    }

}
