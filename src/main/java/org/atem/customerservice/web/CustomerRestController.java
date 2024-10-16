package org.atem.customerservice.web;

import org.atem.customerservice.dao.entities.Customer;
import org.atem.customerservice.dao.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {
@Autowired
CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }
    @GetMapping("/customer/{id}")
    public Customer customerById(Integer id){
        return customerRepository.findById(id).get();
    }
    @PostMapping("/saveCustomer")
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

}
