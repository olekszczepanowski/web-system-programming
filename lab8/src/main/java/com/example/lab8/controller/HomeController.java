package com.example.lab8.controller;

import com.example.lab8.service.CategoryService;
import com.example.lab8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getProductList());
        model.addAttribute("categories", categoryService.getCategoryList());
        return "index";
    }
}
