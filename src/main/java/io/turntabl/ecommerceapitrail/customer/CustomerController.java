package io.turntabl.ecommerceapitrail.customer;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomers(@RequestBody Customer customer){
        return customerService.addCustomers(customer);
    }

    @GetMapping(path = "{customerID}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerID") Long customerID){
        return customerService.getCustomer(customerID);
    }

    @DeleteMapping(path = "{customerID}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerID") Long customerID){
        return customerService.deleteCustomer(customerID);
    }

    @PutMapping(path = "{customerID}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerID") Long customerId, @NotNull @RequestBody Customer updatedCustomer){
        return customerService.updateCustomer(customerId, updatedCustomer);
    }
}
