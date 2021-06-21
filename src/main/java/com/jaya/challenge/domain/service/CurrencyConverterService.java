package com.jaya.challenge.domain.service;

import com.jaya.challenge.dto.CurrencyApiRequestDto;
import com.jaya.challenge.dto.CurrencyApiResponseDto;
import com.jaya.challenge.dto.CurrencyRequestDto;
import com.jaya.challenge.rest.CurrencyExchangeClient;
import com.jaya.challenge.rest.config.RestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CurrencyConverterService {

    private final CurrencyExchangeClient currencyExchangeClient;
    private final RestConfiguration restConfiguration;

    public CurrencyConverterService(CurrencyExchangeClient currencyExchangeClient,
                                    RestConfiguration restConfiguration) {
        this.currencyExchangeClient = currencyExchangeClient;
        this.restConfiguration = restConfiguration;
    }

    @Cacheable(cacheNames = {"exchangeapi"})
    public Mono<CurrencyApiResponseDto> convertCurrency(String origin, String destination){  //origin is not currently being used
        log.info("Starting to convert currency");
        CurrencyApiRequestDto currencyApiRequestDto = CurrencyApiRequestDto.builder()
                .access_key(restConfiguration.getAccess_key())
                .symbols(destination)
                .build();
        return currencyExchangeClient.getCurrencies(currencyApiRequestDto);
    }
}
