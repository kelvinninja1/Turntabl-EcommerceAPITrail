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
}
