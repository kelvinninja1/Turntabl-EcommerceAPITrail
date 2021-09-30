package io.turntabl.ecommerceapitrail.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productCommandLineRunner(ProductRepository productRepository){
        return args -> {
            Product apple = new Product(
                    "M1 MacBook Pro",
                    BigDecimal.valueOf(10),
                    5);
            Product microsoft = new Product(
                    "SurFace Studio 2",
                    BigDecimal.valueOf(10),
                    5);

            productRepository.saveAll(
                    List.of(apple, microsoft)
            );
        };
    }


}
