package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String toString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Customer Info: " + customer + "\nRoom Info: " + room +
                "\nCheck In Date : " + simpleDateFormat.format(checkInDate) +
                "\nCheck Out Date: " + simpleDateFormat.format(checkOutDate);
    }
}
