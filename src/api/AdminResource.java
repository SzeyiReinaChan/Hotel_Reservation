package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminResource {
    public static AdminResource adminResource;

    private AdminResource(){}

    public static AdminResource getInstance(){
        if (Objects.isNull(adminResource)) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    private static final CustomerService customerService =
            CustomerService.getInstance();

    private static final ReservationService reservationService =
            ReservationService.getInstance();

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
