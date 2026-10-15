package com.idf.testtask.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LimitResponseDto(

        Long id,

        String accountFrom,

        String expenseCategory,

        BigDecimal amountUsd,

        LocalDateTime effectiveFrom
) {
}
