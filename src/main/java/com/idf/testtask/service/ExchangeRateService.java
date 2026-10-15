package com.idf.testtask.service;

import com.idf.testtask.model.entity.Currency;
import com.idf.testtask.model.entity.ExchangeRate;

public interface ExchangeRateService {

    /**
     * Returns existing or new exchange rate between two currencies;
     *
     * @param currencyFrom currency before exchange;
     * @param currencyTo currency after exchange;
     * @return returns exchange rate data.
     */
    ExchangeRate getExchangeRate(Currency currencyFrom, Currency currencyTo);
}
