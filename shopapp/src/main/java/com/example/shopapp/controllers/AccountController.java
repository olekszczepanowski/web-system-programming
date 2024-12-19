package com.example.shopapp.controllers;

import com.example.shopapp.models.AppUser;
import com.example.shopapp.models.RegisterDto;
import com.example.shopapp.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.shopapp.models.Role.*;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";
    }
    @PostMapping("register")
    public String register(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult result, Model model) {
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto", "confirmPassword", "Passwords do not match")
            );
        }
        AppUser appUser = appUserRepository.findByEmail(registerDto.getEmail());

        if(appUser != null) {
            result.addError(
                    new FieldError("registerDto", "email", "Email is already in use")
            );
        }

        if(result.hasErrors()) {
            return "register";
        }
        try{
            var bCryptEncoder = new BCryptPasswordEncoder();
            AppUser newUser = new AppUser();
            newUser.setEmail(registerDto.getEmail());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
            newUser.setRole("ROLE_CUSTOMER");
            appUserRepository.save(newUser);
            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        }
        catch(Exception e) {
            result.addError(new FieldError("registerDto", "email", e.getMessage()));
        }

        return "register";
    }
}
