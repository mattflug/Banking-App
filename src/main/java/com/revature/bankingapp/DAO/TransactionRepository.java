package com.revature.bankingapp.DAO;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByAccount(Account account);

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.date BETWEEN .fromDate AND :toDate")
    List<Transaction> findByAccountIdAndDateBetween(
            @Param("accountId") Integer accountId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate);



//    @Query(value = "SELECT * FROM transactions WHERE date BETWEEN :fromDate AND :toDate", nativeQuery = true)
//    List<Transaction> findByDateBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
