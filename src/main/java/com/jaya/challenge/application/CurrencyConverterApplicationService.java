package com.jaya.challenge.application;

import com.jaya.challenge.domain.model.CurrencyRequest;
import com.jaya.challenge.domain.service.CurrencyConverterService;
import com.jaya.challenge.dto.CurrencyApiResponseDto;
import com.jaya.challenge.dto.CurrencyRequestDto;
import com.jaya.challenge.dto.CurrencyResponseDto;
import com.jaya.challenge.enums.CurrencyType;
import com.jaya.challenge.repository.CurrencyRequestRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class CurrencyConverterApplicationService {

    private final CurrencyConverterService currencyConverterService;
    private final CurrencyRequestRepository currencyRequestRepository;


    public CurrencyConverterApplicationService(CurrencyConverterService currencyConverterService,
                                               CurrencyRequestRepository currencyRequestRepository) {
        this.currencyConverterService = currencyConverterService;
        this.currencyRequestRepository = currencyRequestRepository;
    }

    public Mono<CurrencyResponseDto> convertCurrency(CurrencyRequestDto currencyRequestDto){
       return currencyConverterService.convertCurrency(currencyRequestDto)
               .flatMap(convertedCurrency-> save(currencyRequestDto, convertedCurrency))
               .map(this::currencyRequestToCurrencyResponseDtoMap);
    }

    private Mono<CurrencyRequest> save(CurrencyRequestDto currencyRequestDto, CurrencyApiResponseDto convertedCurrency) {
        return currencyRequestRepository.save(CurrencyRequest.builder()
                .currencyOrigin(currencyRequestDto.getCurrencyOrigin().name())
                .currencyDestination(currencyRequestDto.getCurrencyDestination().name())
                .conversionTax(convertedCurrency.getRates().get(currencyRequestDto.getCurrencyDestination().name()))
                .userId(currencyRequestDto.getUserId())
                .valueDestination(convertedCurrency.getRates().get(currencyRequestDto.getCurrencyDestination().name()).multiply(currencyRequestDto.getValueOrigin()))
                .valueOrigin(currencyRequestDto.getValueOrigin())
                .dateOfRequest(OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()).build());
    }

    public Flux<CurrencyResponseDto> findAll() {
        return  currencyRequestRepository.findAll().map(this::currencyRequestToCurrencyResponseDtoMap);
    }

    private CurrencyResponseDto currencyRequestToCurrencyResponseDtoMap(CurrencyRequest currencyRequest){
        return CurrencyResponseDto.builder()
                .transactionId(currencyRequest.getTransactionId())
                .currencyOrigin(CurrencyType.valueOf(currencyRequest.getCurrencyOrigin()))
                .valueOrigin(currencyRequest.getValueOrigin())
                .userId(currencyRequest.getUserId())
                .valueDestination(currencyRequest.getValueDestination())
                .conversionTax(currencyRequest.getConversionTax())
                .currencyDestination(CurrencyType.valueOf(currencyRequest.getCurrencyDestination()))
                .dateOfRequest(currencyRequest.getDateOfRequest())
                .build();
    }
}
