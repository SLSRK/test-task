package com.idf.testtask.repository;

import com.idf.testtask.model.entity.Currency;
import com.idf.testtask.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByCurrencyFromAndCurrencyToAndClosDate(
            Currency currencyFrom,
            Currency currencyTo,
            LocalDate closDate
    );
}
