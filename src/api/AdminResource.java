package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
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

    public static void addRoom(IRoom room){
        ReservationService.addRoom(room);
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public static Collection<Reservation> displayAllReservations(){
        return ReservationService.getAllReservations();
    }
}
