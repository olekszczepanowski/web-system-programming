package com.example.lab8.controller;


import com.example.lab8.product.Product;
import com.example.lab8.service.CategoryService;
import com.example.lab8.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getProductList());
        model.addAttribute("categories", categoryService.getCategoryList());
        return "product/index";
    }
    @PostMapping("user/products/addToCard")
    public String addToCart(@RequestParam Long productId, HttpServletResponse response, HttpServletRequest request) {
        // Pobierz aktualne ciasteczko koszyka
        Cookie[] cookies = request.getCookies();
        String cart = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cart = cookie.getValue();
            }
        }

        // Dodaj produkt do koszyka (w formacie JSON lub CSV)
        cart += productId + ",";

        Cookie cartCookie = new Cookie("cart", cart);
        cartCookie.setMaxAge(60 * 60); // 1 godzina
        response.addCookie(cartCookie);

        return "redirect:/user/products";
    }

    @GetMapping("product/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategoryList());
        return "product/add";
    }

    @PostMapping("product/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("product/remove")
    public String remove(@RequestParam("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/product/{prodId}/edit")
    public String edit(Model model, @PathVariable Long prodId) {
        Product product = productService.getProductById(prodId);

        if (product == null) {
            throw new IllegalArgumentException("Produkt o ID " + prodId + " nie istnieje.");
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getCategoryList());
        return "/product/edit";
    }


    @PostMapping("/product/edit")
    public String edit(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/product/details")
    public String show(@RequestParam("id") long inputId, Model model) {
        Product product = productService.getProductById(inputId);
        if (product == null) {
            throw new IllegalArgumentException("Produkt o ID " + inputId + " nie istnieje.");
        }
        model.addAttribute("product", product);
        return "product/details";
    }


}
