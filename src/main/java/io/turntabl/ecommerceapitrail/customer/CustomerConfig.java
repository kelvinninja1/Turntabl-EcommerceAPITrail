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
            Customer kicki = new Customer(
                    "Kicki Frisch"
            );
            Customer kelvin = new Customer(
                    "Kelvin Morrison"
            );

            customerRepository.saveAll(
                    List.of(kicki, kelvin)
            );
        };
    }


}
