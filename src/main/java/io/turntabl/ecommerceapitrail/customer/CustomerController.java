package io.turntabl.ecommerceapitrail.customer;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Customer> listCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public List<Object> addCustomers(@RequestBody Customer customer){
        customerService.addCustomers(customer);
        return List.of("Success", customer);
    }
}
