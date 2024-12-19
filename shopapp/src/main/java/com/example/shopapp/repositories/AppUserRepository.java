package com.example.shopapp.repositories;

import com.example.shopapp.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByEmail(String email);
}
