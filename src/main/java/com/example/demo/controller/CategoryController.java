package com.example.demo.controller;

import com.example.demo.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.repository.ProductRepository;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final CategoryRepository CategoryRepository;
    private final ProductRepository ProductRepository;

    @Autowired
    public CategoryController(ProductRepository ProductRepository,CategoryRepository CategoryRepository) {
        this.ProductRepository = ProductRepository;
        this.CategoryRepository=CategoryRepository;
    }
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("categories", CategoryRepository.findAll());
        model.addAttribute("users", ProductRepository.findAll());
        return "home";
    }

    @GetMapping("/newCategory")
    public String showSignUpForm(Category category) {
        return "add-category";
    }

    @PostMapping("/addcategory")
    public String addUser(@Valid Category Category, BindingResult result, Model model) {
        CategoryRepository.save(Category);
        model.addAttribute("users", CategoryRepository.findAll());
        return "indexCategory";
    }
    @GetMapping("/editCategory/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Category category = CategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("category", category);
        return "update-category";
    }
    @PostMapping("/updateCategory/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid Category category,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "update-category";
        }

        CategoryRepository.save(category);
        model.addAttribute("users", CategoryRepository.findAll());
        return "indexCategory";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Category Category = CategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        CategoryRepository.delete(Category);
        model.addAttribute("users", CategoryRepository.findAll());
        return "indexCategory";
    }
}
