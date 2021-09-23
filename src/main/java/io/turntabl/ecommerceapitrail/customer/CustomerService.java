package io.turntabl.ecommerceapitrail.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Transactional
    public void updateCustomer(Long customerID, Map<String, Object> change) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(() -> new IllegalStateException("Customer with ID:" + customerID + " does not exist"));

        String name = change.get("name").toString();
        if (name != null && name.length() > 0 && !Objects.equals(name, customer.getName())) {
            customer.setName(name);
            customer.setDateModified(LocalDate.now());
        }
    }
}
