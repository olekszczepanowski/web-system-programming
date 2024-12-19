package com.example.shopapp.services;

import com.example.shopapp.models.AppUser;
import com.example.shopapp.models.Role;
import com.example.shopapp.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username);
        System.out.println("User role from DB: " + user.getRole());
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), getAuthorities(user.getRole())
        );
    }
    private Collection<GrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority( role));
    }
}
