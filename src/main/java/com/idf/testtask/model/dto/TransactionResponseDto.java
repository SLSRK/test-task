package com.idf.testtask.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto(

        Long id,

        String accountFrom,

        String accountTo,

        String currency,

        BigDecimal amount,

        BigDecimal amountUsd,

        String expenseCategory,

        LocalDateTime dateTime,

        Boolean limitExceeded
) {
}
