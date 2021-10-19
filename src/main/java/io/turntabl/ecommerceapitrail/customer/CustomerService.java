package io.turntabl.ecommerceapitrail.customer;

import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final  CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Customer> addCustomers(Customer customer) {
        if (customer.getName() != null && customer.getName().length() > 0) {
            Customer newCustomer = customerRepository.save(customer);
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Customer details are empty, bad or Un-formatted");
        }

    }

    public ResponseEntity<Customer> getCustomer(Long customerID) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(() -> new NotFoundException("Customer with ID:" + customerID + " does not exist"));
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    public ResponseEntity<Customer> deleteCustomer(Long customerID) {
        checkIfCustomerExists(customerID);
        customerRepository.deleteById(customerID);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Customer> updateCustomer(Long customerID, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(() -> new NotFoundException("Customer with ID:" + customerID + " does not exist"));

        String name = updatedCustomer.getName();
        if (name != null && name.length() > 0) {
            if (Objects.equals(name, customer.getName())) {
                throw new NotAcceptableException("No change Required, Updated details already exist");
            } else {
                BeanUtils.copyProperties(updatedCustomer, customer);
                customer.setId(customerID);
                customer.setDateModified(LocalDate.now());
                customerRepository.save(customer);
                return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
            }
        } else {
            throw new BadRequestException("Customer details are empty, bad or Un-formatted");
        }
    }

    public void checkIfCustomerExists(Long customerID) {
        boolean exists = customerRepository.existsById(customerID);
        if (!exists) {
            throw new NotFoundException("Customer with ID:" + customerID + " does not exist");
        }
    }
}
