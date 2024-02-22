package com.revature.bankingapp.controller;

import com.revature.bankingapp.DTO.LoginRequest;
import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.model.User;
import com.revature.bankingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User newUser) {
        return ResponseEntity.ok(userService.registerNewUser(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return ResponseEntity.ok(userService.login(username, password));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/users/search")
    public ResponseEntity<User> findCustomerByTaxId(@RequestParam String taxId) {
        return ResponseEntity.ok(userService.findCustomerByTaxId(taxId));
    }
}
