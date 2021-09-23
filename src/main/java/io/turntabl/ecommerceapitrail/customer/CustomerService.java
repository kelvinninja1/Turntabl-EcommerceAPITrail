package io.turntabl.ecommerceapitrail.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final  CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomers(Customer customer) {
        if (customer == null) {
            throw new IllegalStateException("Customer details are empty");
        }
        customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerID) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(() -> new IllegalStateException("Customer with ID:" + customerID + " does not exist"));
        return customer;
    }

    public void deleteCustomer(Long customerID) {
        boolean exists = customerRepository.existsById(customerID);
        if (!exists) {
            throw new IllegalStateException("Customer with ID:" + customerID + " does not exist");
        }
        customerRepository.deleteById(customerID);
    }
}
