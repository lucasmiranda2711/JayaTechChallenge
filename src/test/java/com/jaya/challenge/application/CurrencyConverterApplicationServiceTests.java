package com.jaya.challenge.application;

import com.jaya.challenge.domain.service.CurrencyConverterService;
import com.jaya.challenge.repository.CurrencyRequestRepository;
import com.jaya.challenge.rest.CurrencyExchangeClient;
import com.jaya.challenge.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterApplicationServiceTests {

    @Test
    public void shouldReturnAndSaveRequests() {
        var mockedRequestClient = Mockito.mock(CurrencyExchangeClient.class);
        var mockedRepository = Mockito.mock(CurrencyRequestRepository.class);
        var restConfiguration = TestUtils.createRestConfiguration();
        var mockedCurrencyRequestDto = TestUtils.createCurrencyRequestDto();
        var mockedApiResponseDto = TestUtils.createMockedApiResponseDto(mockedCurrencyRequestDto);
        var monoMockedApiResponseDto = TestUtils.createMonoMockedApiResponseDto(mockedCurrencyRequestDto);
        var mockedCurrencyRequest = TestUtils.createCurrencyRequest(mockedCurrencyRequestDto, mockedApiResponseDto);
        var mockedResponseDto = TestUtils.createCurrencyResponseDto(mockedCurrencyRequest);

        Mockito.when(mockedRequestClient
                .getCurrencies(Mockito.any()))
                .thenReturn(monoMockedApiResponseDto);

        Mockito.when(mockedRepository
                .save(Mockito.any()))
                .thenReturn(Mono.just(mockedCurrencyRequest));

        CurrencyConverterService currencyConverterService =
                new CurrencyConverterService(mockedRequestClient, restConfiguration);

        CurrencyConverterApplicationService currencyConverterApplicationService
                = new CurrencyConverterApplicationService(currencyConverterService, mockedRepository);

        var response = currencyConverterApplicationService.convertCurrency(mockedCurrencyRequestDto);
        var responseToDto = response.block();
        var mockedResponseToDto = mockedResponseDto.block();

        assertEquals(responseToDto.getConversionTax(), mockedResponseToDto.getConversionTax());
        assertEquals(responseToDto.getDateOfRequest(), mockedResponseToDto.getDateOfRequest());
        assertEquals(responseToDto.getCurrencyOrigin(), mockedResponseToDto.getCurrencyOrigin());
        assertEquals(responseToDto.getCurrencyDestination(), mockedResponseToDto.getCurrencyDestination());
        assertEquals(responseToDto.getTransactionId(), mockedResponseToDto.getTransactionId());
        assertEquals(responseToDto.getValueDestination(), mockedResponseToDto.getValueDestination());
        assertEquals(responseToDto.getValueOrigin(), mockedResponseToDto.getValueOrigin());
        assertEquals(responseToDto.getUserId(), mockedResponseToDto.getUserId());

    }

}
