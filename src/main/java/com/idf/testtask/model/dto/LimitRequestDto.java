package com.idf.testtask.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record LimitRequestDto(

        @NotBlank(message = "accountFrom cannot be empty")
        @Size(min = 10, max = 10, message = "Should contain exactly 10 chars")
        @Pattern(regexp = "^\\d+$", message = "Should contain only numbers")
        String accountFrom,

        @NotBlank(message = "Currency cannot be empty")
        @Pattern(regexp = "PRODUCT|SERVICE",message = "Can be PRODUCT or SERVICE")
        String expenseCategory,

        @NotNull(message = "Amount cannot be null")
        BigDecimal amountUsd
) {
}
