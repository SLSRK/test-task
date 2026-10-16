package com.idf.testtask.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto(

        Long id,

        String accountFrom,

        String accountTo,

        String currencyShortname,

        BigDecimal amount,

        BigDecimal amountUsd,

        String expenseCategory,

        LocalDateTime dateTime,

        Boolean limitExceeded,

        LimitResponseDto limit
) {
}
