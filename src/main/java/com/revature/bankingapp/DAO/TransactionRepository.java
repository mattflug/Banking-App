package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    // view To-Do's associated with the users account
    List<Transaction> findAllByCreatedBy(Account account);

    @Query(value = "SELECT * FROM transactions WHERE date BETWEEN :fromDate AND :toDate", nativeQuery = true)
    List<Transaction> findByDateBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
