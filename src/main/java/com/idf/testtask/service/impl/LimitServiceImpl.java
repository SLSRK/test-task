package com.idf.testtask.service.impl;

import com.idf.testtask.mapper.LimitMapper;
import com.idf.testtask.model.dto.LimitRequestDto;
import com.idf.testtask.model.dto.LimitResponseDto;
import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Limit;
import com.idf.testtask.repository.LimitRepository;
import com.idf.testtask.service.LimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final LimitMapper limitMapper;

    @Value("${limit.default.amount}")
    private int defaultLimit;

    @Transactional
    public LimitResponseDto setLimit(LimitRequestDto limitRequestDto) {
        log.debug("Setting a new limit for account:{} with expense category:{}",
                limitRequestDto.accountFrom(),
                limitRequestDto.expenseCategory());
        return limitMapper.toDto(limitRepository.save(limitMapper.toEntity(limitRequestDto)));
    }

    public Limit getCurrentLimit(String accountFrom, Category expenseCategory) {
        return limitRepository.findFirstByAccountFromAndExpenseCategoryOrderByEffectiveFromDesc(accountFrom, expenseCategory)
                .orElseGet( () ->
                        Limit.builder()
                                .accountFrom(accountFrom)
                                .expenseCategory(expenseCategory)
                                .amountUsd(BigDecimal.valueOf(defaultLimit))
                                .build()
                );
    }

    public List<LimitResponseDto> getAllLimits(String accountFrom, Category expenseCategory) {
        return limitRepository.findByAccountFromAndExpenseCategory(accountFrom, expenseCategory).stream()
                .map(limit -> limitMapper.toDto(limit))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
