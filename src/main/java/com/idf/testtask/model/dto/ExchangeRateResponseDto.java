package com.idf.testtask.model.dto;

import com.idf.testtask.model.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRateResponseDto(
        LocalDate date,
        Currency base,
        Currency quote,
        BigDecimal rate
) {
}