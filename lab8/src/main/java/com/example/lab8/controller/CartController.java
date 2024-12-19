package com.example.lab8.controller;

import com.example.lab8.product.Product;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.lab8.service.ProductService;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String showCart(HttpServletRequest request, Model model) {
        String cartContent = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    cartContent = cookie.getValue();
                }
            }
        }

        List<Long> productIds = new ArrayList<>();
        if (!cartContent.isEmpty()) {
            String[] ids = cartContent.split(",");
            for (String id : ids) {
                try {
                    productIds.add(Long.parseLong(id));
                } catch (NumberFormatException ignored) {
                }
            }
        }

        List<Product> cartProducts = productService.getProductsByIds(productIds);

        model.addAttribute("cartProducts", cartProducts);

        return "user/cart";
    }
}
