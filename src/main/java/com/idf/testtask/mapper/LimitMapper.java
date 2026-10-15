package com.idf.testtask.mapper;

import com.idf.testtask.model.dto.LimitRequestDto;
import com.idf.testtask.model.dto.LimitResponseDto;
import com.idf.testtask.model.entity.Category;
import com.idf.testtask.model.entity.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        imports = Category.class
)
public interface LimitMapper {

    @Mapping(target = "expenseCategory", expression = "java(Category.valueOf(limitRequestDto.expenseCategory()))")
    @Mapping(target = "effectiveFrom", expression = "java(LocalDateTime.now())")
    Limit toEntity(LimitRequestDto limitRequestDto);

    LimitResponseDto toDto(Limit limit);
}
