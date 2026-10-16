package com.idf.testtask.repository;

import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findFirstByAccountFromAndExpenseCategoryOrderByEffectiveFromDesc(
            String accountFrom,
            Category expenseCategory
    );

    List<Limit> findByAccountFromAndExpenseCategory(
            String accountFrom,
            Category expenseCategory
    );
}
