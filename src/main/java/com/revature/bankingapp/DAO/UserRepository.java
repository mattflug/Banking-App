package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword (String username, String password);
    Optional<User> findByIsAdminFalseAndTaxIdNumber (String taxIdNumber); //finds a customer by tax id
    List<User> findAllByIsAdminFalse(); // finds all customers
}