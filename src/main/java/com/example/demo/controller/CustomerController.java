package com.example.demo.controller;

import com.example.demo.Customer;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.validation.Valid;

@Controller
public class CustomerController {
    private final CustomerRepository CustomerRepository;

    @Autowired
    public CustomerController(CustomerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }
    @GetMapping("/newCustomer")
    public String showSignUpForm(Customer Customer,Model model) {

        model.addAttribute("Customer", Customer);
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addUser(@Valid Customer Customer, BindingResult result, Model model) {
        CustomerRepository.save(Customer);
        model.addAttribute("users", CustomerRepository.findAll());
        return "redirect:/";
    }

}
