package service;

import model.Customer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class CustomerService {
    public static CustomerService customerService;  //reference the class

    private CustomerService(){}

    Collection<Customer> customers = new HashSet<Customer>();

    public static CustomerService getInstance() {   // check anything in the class or not
        if (Objects.isNull(customerService)){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        Customer newCustomer = new Customer(firstName, lastName, email);
        customers.add(newCustomer);
    }

    public Customer getCustomer(String customerEmail){
        for (Customer currentCustomer : customers){
            if (customerEmail.equals(currentCustomer.getEmail())){
                return currentCustomer;
            }
            return null;
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
