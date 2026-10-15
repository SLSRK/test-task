package com.idf.testtask.service.impl;

import com.idf.testtask.client.ExchangeRateClient;
import com.idf.testtask.model.entity.Currency;
import com.idf.testtask.model.entity.ExchangeRate;
import com.idf.testtask.repository.ExchangeRateRepository;
import com.idf.testtask.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateClient exchangeRateClient;

    public ExchangeRate getExchangeRate(Currency currencyFrom, Currency currencyTo) {
        return exchangeRateRepository.findByCurrencyFromAndCurrencyToAndClosDate(
                currencyFrom,
                currencyTo,
                LocalDate.now()
        ).orElseGet( () -> createExchangeRateHelper(currencyFrom,currencyTo));
    }

    @Transactional
    private ExchangeRate createExchangeRateHelper(Currency currencyFrom, Currency currencyTo) {
        log.debug("Creating a new exchange rate record");
        return exchangeRateRepository.save(
                ExchangeRate.builder()
                .currencyFrom(currencyFrom)
                .currencyTo(currencyTo)
                .closeRate(exchangeRateClient.getCloseRate(currencyFrom,currencyTo))
                .closDate(LocalDate.now())
                .build()
        );
    }
}
