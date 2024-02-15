package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByAccountHolder (User accountHolder);

//    1. save built in, find by id built in

//    2. find all built in
//    3. delete by id built in
}
