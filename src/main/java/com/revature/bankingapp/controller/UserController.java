package com.revature.bankingapp.controller;

import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.model.User;
import com.revature.bankingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping
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
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Transaction>> viewAllCustomers() {
        List<User> viewAllCustomers = userService.viewAllCustomers();
        return ResponseEntity.ok(userService.viewAllCustomers);
    }

    @GetMapping("/users/{tax_id_number}")
    public ResponseEntity<User> findCustomerByTaxId(@PathVariable String taxId) {
        return ResponseEntity.ok(userService.findCustomerByTaxId(taxId));
    }
}
