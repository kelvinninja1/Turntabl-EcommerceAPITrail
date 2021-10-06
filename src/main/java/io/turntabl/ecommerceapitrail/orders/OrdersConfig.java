package io.turntabl.ecommerceapitrail.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrdersConfig {

    @Bean
    CommandLineRunner orderCommandLineRunner(OrdersRepository ordersRepository){
        return args -> {
            Orders order1 = new Orders(
                    Long.parseLong("1"));
            Orders order2 = new Orders(
                    Long.parseLong("2"));

            ordersRepository.saveAll(
                    List.of(order1, order2)
            );
        };
    }


}
