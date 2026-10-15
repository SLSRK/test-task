package com.idf.testtask.controller;

import com.idf.testtask.model.dto.LimitRequestDto;
import com.idf.testtask.model.dto.LimitResponseDto;
import com.idf.testtask.model.entity.Category;
import com.idf.testtask.service.LimitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/limit")
@RequiredArgsConstructor
public class LimitController {

    private final LimitService limitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LimitResponseDto setLimit(@RequestBody @Valid LimitRequestDto limitRequestDto) {
        return limitService.setLimit(limitRequestDto);
    }

    @GetMapping("/{accountFrom}/{expenseCategory}")
    @ResponseStatus(HttpStatus.OK)
    public List<LimitResponseDto> getAllLimits(@PathVariable String accountFrom,
                                               @PathVariable Category expenseCategory) {
        return limitService.getAllLimits(accountFrom, expenseCategory);
    }
}
