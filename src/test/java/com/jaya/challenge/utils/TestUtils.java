package com.jaya.challenge.utils;

import com.jaya.challenge.domain.model.CurrencyRequest;
import com.jaya.challenge.dto.CurrencyApiRequestDto;
import com.jaya.challenge.dto.CurrencyApiResponseDto;
import com.jaya.challenge.dto.CurrencyRequestDto;
import com.jaya.challenge.dto.CurrencyResponseDto;
import com.jaya.challenge.enums.CurrencyType;
import com.jaya.challenge.rest.config.RestConfiguration;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    private static final String ACCESS_KEY = "123";

    static public Mono<CurrencyApiResponseDto> createMonoMockedApiResponseDto(CurrencyRequestDto mockedCurrencyRequest) {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(mockedCurrencyRequest.getCurrencyDestination().name(), new BigDecimal("10"));

        return Mono.just(CurrencyApiResponseDto.builder()
                .base(mockedCurrencyRequest.getCurrencyOrigin().name())
                .date(LocalDate.now())
                .success(true)
                .timestamp(123456)
                .rates(rates)
                .build());
    }
    static public CurrencyApiResponseDto createMockedApiResponseDto(CurrencyRequestDto mockedCurrencyRequest) {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(mockedCurrencyRequest.getCurrencyDestination().name(), new BigDecimal("10"));

        return CurrencyApiResponseDto.builder()
                .base(mockedCurrencyRequest.getCurrencyOrigin().name())
                .date(LocalDate.now())
                .success(true)
                .timestamp(123456)
                .rates(rates)
                .build();
    }


    static public CurrencyApiRequestDto createCurrencyApiRequestDto(RestConfiguration restConfiguration,
                                                              CurrencyRequestDto currencyRequestDto){
        return CurrencyApiRequestDto.builder()
                .access_key(restConfiguration.getAccess_key())
                .symbols(currencyRequestDto.getCurrencyDestination().name())
                .build();
    }
    static public CurrencyRequestDto createCurrencyRequestDto() {
        return new CurrencyRequestDto(123, CurrencyType.EUR,
                new BigDecimal("100"), CurrencyType.USD);
    }

    static public CurrencyRequest createCurrencyRequest(CurrencyRequestDto currencyRequestDto, CurrencyApiResponseDto convertedCurrency) {
        return CurrencyRequest.builder()
                .currencyOrigin(currencyRequestDto.getCurrencyOrigin().name())
                .currencyDestination(currencyRequestDto.getCurrencyDestination().name())
                .conversionTax(convertedCurrency.getRates().get(currencyRequestDto.getCurrencyDestination().name()))
                .userId(currencyRequestDto.getUserId())
                .valueDestination(convertedCurrency.getRates().get(currencyRequestDto.getCurrencyDestination().name()).multiply(currencyRequestDto.getValueOrigin()))
                .valueOrigin(currencyRequestDto.getValueOrigin())
                .dateOfRequest(OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()).build();
    }

    static public Mono<CurrencyResponseDto> createCurrencyResponseDto(CurrencyRequest currencyRequest) {
        return Mono.just(CurrencyResponseDto.builder()
                .transactionId(currencyRequest.getTransactionId())
                .currencyOrigin(CurrencyType.valueOf(currencyRequest.getCurrencyOrigin()))
                .valueOrigin(currencyRequest.getValueOrigin())
                .userId(currencyRequest.getUserId())
                .valueDestination(currencyRequest.getValueDestination())
                .conversionTax(currencyRequest.getConversionTax())
                .currencyDestination(CurrencyType.valueOf(currencyRequest.getCurrencyDestination()))
                .dateOfRequest(currencyRequest.getDateOfRequest())
                .build());
    }

    static public RestConfiguration createRestConfiguration() {
        var restConfiguration = new RestConfiguration();
        restConfiguration.setAccess_key(ACCESS_KEY);
        return restConfiguration;
    }
}
