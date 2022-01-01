package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    public static  ReservationService reservationService;

    private ReservationService(){}

    public static Collection<IRoom> rooms = new HashSet<>();
    public static Collection<Reservation> reservations = new HashSet<>();

    public static ReservationService getInstance() {
        if (Objects.isNull(reservationService)){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public static void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        for (IRoom room: rooms){
            if (roomId.equals(room.getRoomNumber())){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
//        Collection<Reservation> makeReservations = getCustomersReservation(customer);
//        if (makeReservations == null){
//            makeReservations = new LinkedList<>();
//        }
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> availableRooms = new HashSet<>();

        if (reservations.isEmpty()){
            availableRooms = rooms;
            return availableRooms;
        }
        else{
            for(IRoom room:rooms){
                for(Reservation reservation : reservations){
                    //check to see if the room is available, if so add to available list, if not remove
                    if((room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) &&
                    ((checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate()))
                            || (checkInDate.after(reservation.getCheckInDate()) && (checkOutDate.after(reservation.getCheckOutDate()))))){
                        availableRooms.add(room);
                    }
                    else{
                        if (room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())){
                            availableRooms.remove(room);
                        }
                    }
                }
            }
        } System.out.println(availableRooms);
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersReservation = new HashSet<>();
            for(Reservation reservation : reservations){
                if(reservation.getCustomer().equals(customer)){
                    customersReservation.add(reservation);
                }
            }return customersReservation;
    }

    public Collection<IRoom> getRooms(){
        return rooms;
    }

    public static Collection<Reservation> getAllReservations() {
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
        return new HashSet<>(reservations);
    }
}
