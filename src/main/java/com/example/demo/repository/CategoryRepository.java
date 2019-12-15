package com.example.demo.repository;import com.example.demo.Product;


import com.example.demo.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);

}
