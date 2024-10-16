package org.atem.customerservice.dao.repositories;

import org.atem.customerservice.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
