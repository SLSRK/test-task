package com.idf.testtask.service;

import com.idf.testtask.model.dto.TransactionRequestDto;
import com.idf.testtask.model.dto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    /**
     * Create a new transaction and process it's data;
     *
     * @param transactionRequestDto data of the transaction to add;
     * @return returns the data of the saved transaction.
     */
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    /**
     * Get a transaction by it's ID;
     *
     * @param id ID of the transaction to get;
     * @return returns the data of the transaction.
     */
    TransactionResponseDto getTransactionById(Long id);

    /**
     * Get all transactions, which exceeded the relevant limit then they were created;
     *
     * @return returns the list of transactions data.
     */
    List<TransactionResponseDto> getAllTransactionsWithLimitExceeded();
}
