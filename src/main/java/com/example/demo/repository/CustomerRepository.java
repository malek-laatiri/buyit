package com.example.demo.repository;

import com.example.demo.Customer;
import com.example.demo.Customer_order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
