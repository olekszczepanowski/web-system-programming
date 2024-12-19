package com.example.lab8.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN")
                        .build()
        );

        userDetailsManager.createUser(
                User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .roles("USER")
                        .build()
        );

        return userDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Dostęp tylko dla administratorów
                        .requestMatchers("/testuser/**").authenticated() // Zalogowani użytkownicy
                        .anyRequest().permitAll() // Pozwól na inne żądania
                )
                .formLogin(form -> form
                        .loginPage("/login") // Ścieżka do niestandardowej strony logowania
                        .defaultSuccessUrl("/user/products", true) // Przekierowanie po zalogowaniu
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Ścieżka wylogowania
                        .logoutSuccessUrl("/login?logout") // Przekierowanie po wylogowaniu
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public SecurityFilterChain h2FilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }

}
