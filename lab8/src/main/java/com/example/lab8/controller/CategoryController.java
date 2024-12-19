package com.example.lab8.controller;

import com.example.lab8.product.Category;
import com.example.lab8.product.Product;
import com.example.lab8.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("category/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/";
    }

    @GetMapping("/category/remove")
    public String removeCategory(@RequestParam("id") long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/";
    }

    @GetMapping("/category/{categoryId}/edit")
    public String edit(Model model, @PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Kategoria o ID " + categoryId + " nie istnieje.");
        }
        model.addAttribute("category", category);
        return "/category/edit";
    }


    @PostMapping("/category/edit")
    public String edit(@ModelAttribute Category category) {
        categoryService.updateCategory(category);
        return "redirect:/";
    }

    @GetMapping("/category/details")
    public String show(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(inputId));
        return "category/details";
    }
}
