package io.turntabl.ecommerceapitrail.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productCommandLineRunner(ProductRepository productRepository){
        return args -> {
            Product apple = new Product(
                    "M1 MacBook Pro"
            );
            Product microsoft = new Product(
                    "SurFace Studio 2"
            );

            productRepository.saveAll(
                    List.of(apple, microsoft)
            );
        };
    }


}
