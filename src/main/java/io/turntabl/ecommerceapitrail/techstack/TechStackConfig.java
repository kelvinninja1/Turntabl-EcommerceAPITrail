package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TechStackConfig {
    @Bean
    CommandLineRunner commandLineRunner( TechStackRepository techStackRepository){
        return args -> {
            TechStack primaryLanguage = new TechStack(
                    "Java",
                    Boolean.TRUE
            );

            TechStack primaryFramework = new TechStack(
                    "Spring Boot",
                    Boolean.TRUE
            );

            techStackRepository.saveAll(
                    List.of(primaryLanguage, primaryFramework)
            );
        };
    }
}
