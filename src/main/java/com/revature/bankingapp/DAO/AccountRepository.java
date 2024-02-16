package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByAccountHolder (User accountHolder);

    Optional<Account> findByAccountNumber(String accountNumber);
}
