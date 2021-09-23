package io.turntabl.ecommerceapitrail.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner customerCommandLineRunner(CustomerRepository customerRepository){
        return args -> {
            Customer maya = new Customer(
                    "Maya Holiday"
            );
            Customer nathan = new Customer(
                    "Nathan Banks"
            );

            customerRepository.saveAll(
                    List.of(maya, nathan)
            );
        };
    }


}
