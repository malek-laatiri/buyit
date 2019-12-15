package com.example.demo.controller;

import com.example.demo.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final ProductRepository ProductRepository;

    @Autowired
    public ProductController(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }


    @GetMapping("/signup")
    public String showSignUpForm(Product product) {
        return "add-user";
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
