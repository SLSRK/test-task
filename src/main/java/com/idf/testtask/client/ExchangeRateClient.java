package com.idf.testtask.client;

import com.idf.testtask.model.dto.ExchangeRateResponseDto;
import com.idf.testtask.model.entity.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class ExchangeRateClient {

    private final RestClient restClient;

    @Value("${exchange-rate.url}")
    private String exchangeRateUrl;

    public BigDecimal getCloseRate(Currency currencyFrom, Currency currencyTo){
        return restClient.get()
                .uri(URI.create(exchangeRateUrl + "/v2/rate/"
                        + currencyFrom.name().toLowerCase() + "/"
                        + currencyTo.name().toLowerCase()))
                .retrieve()
                .body(ExchangeRateResponseDto.class).rate();
    }
}
