package com.example.demo.repository;

import com.example.demo.Category;
import com.example.demo.Ordered_product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ordered_productRepository extends CrudRepository<Ordered_product, Long> {

}
