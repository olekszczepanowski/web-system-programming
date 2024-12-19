package com.example.lab8.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
@Setter
@Getter
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
