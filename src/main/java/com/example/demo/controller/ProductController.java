package com.example.demo.controller;

import com.example.demo.Category;
import com.example.demo.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ProductController {
    private final ProductRepository ProductRepository;
    private final CategoryRepository CategoryRepository;


    @Autowired
    public ProductController(ProductRepository ProductRepository,CategoryRepository CategoryRepository) {
        this.ProductRepository = ProductRepository;
        this.CategoryRepository=CategoryRepository;
    }


    @GetMapping("/signup")
    public String showSignUpForm(Product product,Model model) {
        model.addAttribute("categories", CategoryRepository.findAll());

        return "add-user";
    }
    @GetMapping("/productsByCategory/{id}")
    public String productsByCategory(@PathVariable("id") Category id,Model model) {
        model.addAttribute("users", ProductRepository.findByCategory(id));


        return "productbycategory";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Product product, BindingResult result, Model model) {
        ProductRepository.save(product);
        model.addAttribute("users", ProductRepository.findAll());
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product user = ProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid Product product,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-user";
        }

        ProductRepository.save(product);
        model.addAttribute("users", ProductRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Product product = ProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        ProductRepository.delete(product);
        model.addAttribute("users", ProductRepository.findAll());
        return "index";
    }
}
