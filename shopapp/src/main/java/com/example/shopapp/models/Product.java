package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double weight;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(Long id, String name, double weight, double price, Category category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = category;
    }
    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

