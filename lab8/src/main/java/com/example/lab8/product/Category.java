package com.example.lab8.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, orphanRemoval = false)
    private List<Product> products;
}
