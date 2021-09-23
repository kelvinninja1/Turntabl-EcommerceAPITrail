package io.turntabl.ecommerceapitrail.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> listCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addCustomers(@RequestBody Customer customer){
        customerService.addCustomers(customer);
        return List.of("Success", customer);
    }

    @GetMapping(path = "{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("customerID") Long customerID){
        return customerService.getCustomer(customerID);
    }

    @DeleteMapping(path = "{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteCustomer(@PathVariable("customerID") Long customerID){
        customerService.deleteCustomer(customerID);
        return List.of("Success");
    }
}
