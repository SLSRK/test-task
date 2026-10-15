package com.idf.testtask.service;

import com.idf.testtask.model.dto.LimitRequestDto;
import com.idf.testtask.model.dto.LimitResponseDto;
import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Limit;

import java.util.List;

public interface LimitService {

    /**
     * Set a new or update existing limit for account and category;
     *
     * @param limitRequestDto data of the limit to set;
     * @return returns saved limit data.
     */
    LimitResponseDto setLimit(LimitRequestDto limitRequestDto);

    /**
     * Get the latest or default limit for account and category;
     *
     * @param accountFrom account of the limit;
     * @param expenseCategory category of the limit;
     * @return returns the relevant limit data.
     */
    Limit getCurrentLimit(String accountFrom, Category expenseCategory);

    /**
     * Get list of all limits, that were applied for account and category;
     *
     * @param accountFrom account of the limit;
     * @param expenseCategory category of the limit;
     * @return returns the list of limits data.
     */
    List<LimitResponseDto> getAllLimits (String accountFrom, Category expenseCategory);
}
