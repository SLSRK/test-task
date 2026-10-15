package com.idf.testtask.service.impl;

import com.idf.testtask.exception.ConflictException;
import com.idf.testtask.exception.NotFoundException;
import com.idf.testtask.mapper.TransactionMapper;
import com.idf.testtask.model.dto.TransactionRequestDto;
import com.idf.testtask.model.dto.TransactionResponseDto;
import com.idf.testtask.model.entity.Currency;
import com.idf.testtask.model.entity.Limit;
import com.idf.testtask.model.entity.Transaction;
import com.idf.testtask.repository.TransactionRepository;
import com.idf.testtask.service.ExchangeRateService;
import com.idf.testtask.service.LimitService;
import com.idf.testtask.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrancastionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final ExchangeRateService exchangeRateService;
    private final LimitService limitService;

    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto){
        log.debug("Creating a new transaction");
        if(transactionRequestDto.accountFrom().equals(transactionRequestDto.accountTo())) {
            throw new ConflictException("Transactions on the same account are forbidden");
        }

        Transaction transaction = transactionMapper.toEntity(transactionRequestDto);
        if (transaction.getCurrency().equals(Currency.USD)){
            transaction.setAmountUsd(transaction.getAmount());
        } else {
            transaction.setAmountUsd(
                    transaction.getAmount()
                            .multiply(exchangeRateService.getExchangeRate(
                                    transaction.getCurrency(),
                                    Currency.USD)
                                    .getCloseRate())
                            .setScale(2, RoundingMode.HALF_UP)
            );
        }

        List<Transaction> transactions = transactionRepository
                .findByAccountFromAndExpenseCategoryAndDateTimeGreaterThanEqualAndDateTimeLessThan(
                        transaction.getAccountFrom(),
                        transaction.getExpenseCategory(),
                        LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                        LocalDate.now().withDayOfMonth(1).atStartOfDay().plusMonths(1)
        );
        BigDecimal totalAmount = transactions.stream()
                .map(Transaction::getAmountUsd)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(transaction.getAmountUsd());
        Limit limit = limitService.getCurrentLimit(
                transaction.getAccountFrom(),
                transaction.getExpenseCategory());
        transaction.setLimitExceeded(totalAmount.compareTo(limit.getAmountUsd()) > 0);

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    public TransactionResponseDto getTransactionById(Long id) {
        return transactionMapper.toDto(transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found")));
    }

    public List<TransactionResponseDto> getAllTransactionsWithLimitExceeded() {
        return transactionRepository.findByLimitExceededTrue().stream()
                .map(transaction -> transactionMapper.toDto(transaction))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
