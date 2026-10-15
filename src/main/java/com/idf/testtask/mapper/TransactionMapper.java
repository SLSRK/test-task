package com.idf.testtask.mapper;

import com.idf.testtask.model.dto.TransactionRequestDto;
import com.idf.testtask.model.dto.TransactionResponseDto;
import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Currency;
import com.idf.testtask.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        imports = {
                Category.class,
                Currency.class
        }
)
public interface TransactionMapper {

    @Mapping(target = "currency", expression = "java(Currency.valueOf(transactionRequestDto.currency()))")
    @Mapping(target = "expenseCategory", expression = "java(Category.valueOf(transactionRequestDto.expenseCategory()))")
    @Mapping(target = "dateTime", expression = "java(LocalDateTime.now())")
    Transaction toEntity(TransactionRequestDto transactionRequestDto);

    TransactionResponseDto toDto(Transaction transaction);
}
