package com.example.demo.controller;

import com.example.demo.Customer;
import com.example.demo.Ordered_product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.Ordered_productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository CustomerRepository;
    private final com.example.demo.repository.Ordered_productRepository Ordered_productRepository;

    @Autowired
    public CustomerController(CustomerRepository CustomerRepository,Ordered_productRepository Ordered_productRepository) {
        this.Ordered_productRepository = Ordered_productRepository;

        this.CustomerRepository = CustomerRepository;
    }
    @GetMapping("/newCustomer")
    public String showSignUpForm(Customer Customer,Model model) {
        int somme=0;
        List<Ordered_product> counts = new ArrayList<>();
        Ordered_productRepository.findAll().forEach(counts::add);
        for (int i=0;i<counts.size();i++){
            Ordered_product current = counts.get(i);
            System.out.println(current.getId());
            somme=somme+current.getQuantity() * current.getProduct().getPrice();
        }

        model.addAttribute("somme", somme);

        model.addAttribute("Customer", Customer);
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addUser(@Valid Customer Customer, BindingResult result, Model model) {
        CustomerRepository.save(Customer);
        model.addAttribute("users", Ordered_productRepository.findAll());
        int somme=0;
        List<Ordered_product> counts = new ArrayList<>();
        Ordered_productRepository.findAll().forEach(counts::add);
        for (int i=0;i<counts.size();i++){
            Ordered_product current = counts.get(i);
            System.out.println(current.getId());
            somme=somme+current.getQuantity() * current.getProduct().getPrice();
        }

        model.addAttribute("Customer", Customer);
        model.addAttribute("somme", somme);

        return "comfirmation";
    }

}
