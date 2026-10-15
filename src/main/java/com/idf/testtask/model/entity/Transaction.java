package com.idf.testtask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", nullable = false)
    private String accountFrom;

    @Column(name = "account_to", nullable = false)
    private String accountTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3)
    private Currency currency;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "amount_usd", nullable = false, precision = 19, scale = 2)
    private BigDecimal amountUsd;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category", length = 7)
    private Category expenseCategory;

    @CreatedDate
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded;
}
