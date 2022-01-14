package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Objects;
import java.util.Date;
import java.util.regex.Pattern;

public class HotelResource {
    public static HotelResource hotelResource;
    public static ReservationService reservationService = ReservationService.getInstance();

    private HotelResource(){}

    public static HotelResource getInstance() {
        if (Objects.isNull(hotelResource)){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    private static final CustomerService customerService =
            CustomerService.getInstance();


    private static final String emailRegex = "^(.+)@(.+).com$";
    private static final Pattern pattern = Pattern.compile(emailRegex);

    public static Customer getCustomer(String email){
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email");
        }
        return customerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom (Date checkInDate, Date checkOutDate){
        Collection<IRoom> rooms = reservationService.findRooms(checkInDate,checkOutDate);
        if (rooms == null) return null;
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
