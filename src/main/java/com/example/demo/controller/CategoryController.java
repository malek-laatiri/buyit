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

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final CategoryRepository CategoryRepository;

    @Autowired
    public CategoryController(CategoryRepository CategoryRepository) {
        this.CategoryRepository = CategoryRepository;
    }

    @GetMapping("/addCategory")
    public String showSignUpForm(Category Category) {
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
        Category user = CategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-Category";
    }
    @PostMapping("/updateCategory/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid Category Category,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            Category.setId(id);
            return "update-Category";
        }

        CategoryRepository.save(Category);
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
