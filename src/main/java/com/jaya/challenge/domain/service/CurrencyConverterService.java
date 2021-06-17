package com.jaya.challenge.domain.service;

import com.jaya.challenge.dto.CurrencyApiRequestDto;
import com.jaya.challenge.dto.CurrencyApiResponseDto;
import com.jaya.challenge.dto.CurrencyRequestDto;
import com.jaya.challenge.rest.CurrencyExchangeClient;
import com.jaya.challenge.rest.config.RestConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CurrencyConverterService {

    private final CurrencyExchangeClient currencyExchangeClient;
    private final RestConfiguration restConfiguration;

    public CurrencyConverterService(CurrencyExchangeClient currencyExchangeClient,
                                    RestConfiguration restConfiguration) {
        this.currencyExchangeClient = currencyExchangeClient;
        this.restConfiguration = restConfiguration;
    }

    public Mono<CurrencyApiResponseDto> convertCurrency(CurrencyRequestDto currencyRequestDto){
        CurrencyApiRequestDto currencyApiRequestDto = CurrencyApiRequestDto.builder()
                .access_key(restConfiguration.getAccess_key())
                .symbols(currencyRequestDto.getCurrencyDestination().name())
                .build();
        //return Mono.just(new CurrencyApiResponseDto());
        return currencyExchangeClient.getCurrencies(currencyApiRequestDto);
    }
}
