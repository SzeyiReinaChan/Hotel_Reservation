package application;

import api.HotelResource;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        actions();
    }

    public static void menu() {
        System.out.println("Main Menu\n---------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account \n4. Admin \n5. Exit");
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
                        exit();
                        runMainMenu = false;
                        break;
                    default:
                        System.out.println("Please select 1 - 5 for the menu option");
                }
            }
            catch (Exception ex) {
                System.out.println("Please select 1 - 5 for the menu option");
            }
        }
    }

    public static void findAndReserveARoom(){
        System.out.println("a");
    }

    public static void seeMyReservation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email: ");
        try {
            String email = scanner.nextLine();
            String customerName = HotelResource.getCustomer(email).getFullName();
        }
    }

    public static void createAnAccount(){
        System.out.println("c");
    }

    public static void adminMenu(){
        System.out.println("d");
    }

    public static void exit(){
        System.out.println("Exiting Application");
    }
}

