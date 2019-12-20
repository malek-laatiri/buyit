package com.example.demo.repository;

import com.example.demo.Customer_order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_orderRepository extends CrudRepository<Customer_order, Long> {
}
