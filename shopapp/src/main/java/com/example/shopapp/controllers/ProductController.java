package com.example.shopapp.controllers;

import com.example.shopapp.models.Product;
import com.example.shopapp.services.CategoryService;
import com.example.shopapp.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
    public class ProductController {
        private final ProductService productService;
        private final CategoryService categoryService;

        public ProductController(ProductService productService, CategoryService categoryService) {
            this.productService = productService;
            this.categoryService = categoryService;
        }

        @GetMapping("/products")
        public String showProducts(Model model) {
            model.addAttribute("products", productService.getProductList());
            model.addAttribute("serverTime", System.currentTimeMillis());
            return "products/index";
        }


    @PostMapping("/products/addToCart")
    public String addToCart(@RequestParam("productId") Long productId, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        String cartJson = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Integer> cartItems = cartJson.isEmpty()
                ? new HashMap<>()
                : objectMapper.readValue(cartJson, new TypeReference<Map<Long, Integer>>() {});

        cartItems.put(productId, cartItems.getOrDefault(productId, 0) + 1);

        cartJson = objectMapper.writeValueAsString(cartItems);

        Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartJson, StandardCharsets.UTF_8));
        cartCookie.setPath("/");
        cartCookie.setHttpOnly(true);
        response.addCookie(cartCookie);

        return "redirect:/products";
    }



    @GetMapping("/cart")
    public String showCart(HttpServletRequest request, Model model) {
        try {
            Cookie[] cookies = request.getCookies();
            String cartJson = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                        break;
                    }
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<Long, Integer> cartItems = cartJson.isEmpty()
                    ? new HashMap<>()
                    : objectMapper.readValue(cartJson, new TypeReference<Map<Long, Integer>>() {});

            List<Map<String, Object>> productsInCart = new ArrayList<>();
            double totalValue = 0.0;

            for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
                Product product = productService.getProductById(entry.getKey());
                if (product != null) {
                    Map<String, Object> productWithQuantity = new HashMap<>();
                    productWithQuantity.put("product", product);
                    productWithQuantity.put("quantity", entry.getValue());
                    productsInCart.add(productWithQuantity);

                
                    totalValue += product.getPrice() * entry.getValue();
                }
            }

            model.addAttribute("productsInCart", productsInCart);
            model.addAttribute("totalValue", totalValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cart/index";
    }


    @PostMapping("/cart/update")
    public String updateCart(@RequestParam("productId") Long productId,
                             @RequestParam("quantity") Integer quantity,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            String cartJson = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                        break;
                    }
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<Long, Integer> cartItems = cartJson.isEmpty()
                    ? new HashMap<>()
                    : objectMapper.readValue(cartJson, new TypeReference<Map<Long, Integer>>() {});

            if (quantity <= 0) {
                cartItems.remove(productId);
            } else {
                cartItems.put(productId, quantity);
            }

            cartJson = objectMapper.writeValueAsString(cartItems);
            Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartJson, StandardCharsets.UTF_8));
            cartCookie.setPath("/");
            cartCookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cartCookie);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart";
    }



    @GetMapping("/product")
        public String listProducts(Model model) {
            model.addAttribute("products", productService.getProductList());
            model.addAttribute("categories", categoryService.getCategoryList());
            return "product/index";
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
            return "redirect:/product/index";
        }

        @GetMapping("product/remove")
        public String remove(@RequestParam("id") long id) {
            productService.deleteProductById(id);
            return "redirect:/product/index";
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
            return "redirect:/product/index";
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

