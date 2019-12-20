package com.example.demo.controller;

import com.example.demo.Customer_order;
import com.example.demo.Ordered_product;
import com.example.demo.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.Customer_orderRepository;
import com.example.demo.repository.Ordered_productRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class Ordered_productController {
    private final Ordered_productRepository Ordered_productRepository;
    private final ProductRepository ProductRepository;
    private final Customer_orderRepository Customer_orderRepository;


    @Autowired
    public Ordered_productController(Customer_orderRepository Customer_orderRepository,ProductRepository ProductRepository,Ordered_productRepository Ordered_productRepository) {
        this.Ordered_productRepository = Ordered_productRepository;
        this.ProductRepository = ProductRepository;
        this.Customer_orderRepository=Customer_orderRepository;

    }

    @GetMapping("/addOrder/{id}")
    public String showSignUpForm(@PathVariable("id") long id,Ordered_product Ordered_product,Model model) {
        Product user = ProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        model.addAttribute("Ordered_product", Ordered_product);

        return "add-Ordered_product";
    }


    @PostMapping("/saveOrder")
    public String addUser(@Valid Ordered_product Ordered_product, BindingResult result, Model model) {
        Customer_order Customer_order= new Customer_order();
        Customer_orderRepository.save(Customer_order);
        Ordered_product.setCustomer_order(Customer_order);
        Ordered_productRepository.save(Ordered_product);
        model.addAttribute("users", Ordered_productRepository.findAll());
        return "indexOrdered_product";
    }
    @GetMapping("/editOrdered_product/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Ordered_product user = Ordered_productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/updateOrdered_product/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid Ordered_product Ordered_product,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            Ordered_product.setId(id);
            return "update-user";
        }

        Ordered_productRepository.save(Ordered_product);
        model.addAttribute("users", Ordered_productRepository.findAll());
        return "indexOredered_index";
    }


}
