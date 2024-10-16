package org.atem.customerservice;

import org.atem.customerservice.dao.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.atem.customerservice.dao.entities.Customer;

@SpringBootApplication
public class CustomerServiceApplication {
//    @EnableJpaRepositories(basePackages="org.atem.customerservice.dao.repositories")
//    @EntityScan(basePackages="org.atem.customerservice.dao.entities")
    @SpringBootApplication
    public class CustomerDataServiceApplication {
        @Bean
        CommandLineRunner start(CustomerRepository customerRepository) {
            return args -> {
                customerRepository.save(Customer.builder().id(1).name("X").email("X@gmail.com").build());
                customerRepository.save(Customer.builder().id(2).name("Y").email("Y@gmail.com").build());
            };
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
