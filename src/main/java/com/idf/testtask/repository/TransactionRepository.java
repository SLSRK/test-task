package com.idf.testtask.repository;

import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountFromAndExpenseCategoryAndDateTimeGreaterThanEqualAndDateTimeLessThan(
            String accountFrom,
            Category expenseCategory,
            LocalDateTime startOfMonth,
            LocalDateTime startOfNextMonth
    );

    List<Transaction> findByLimitExceededTrue();
}
