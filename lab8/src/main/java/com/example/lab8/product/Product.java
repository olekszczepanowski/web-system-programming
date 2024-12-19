package com.example.lab8.product;



import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double weight;
    private double price;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}

