package com.revature.bankingapp.service;

import com.revature.bankingapp.DAO.UserRepository;
import com.revature.bankingapp.exception.AccessDeniedException;
import com.revature.bankingapp.exception.UsernameAlreadyExistsException;
import com.revature.bankingapp.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerNewUser(User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        return userRepository.save(newUser);
    }


    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new AccessDeniedException("Invalid username or password"));
    }

    public User findCustomerByTaxId(String taxId) {
        return userRepository.findByIsAdminFalseAndTaxIdNumber(taxId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        }
    }


