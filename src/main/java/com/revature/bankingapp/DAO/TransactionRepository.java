package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    // view To-Do's associated with the users account
    List<Transaction> findAllByCreatedBy(Account account);

//    1. transaction repository save (built in)

//    2. transactionrepo custom querry - do research

}
