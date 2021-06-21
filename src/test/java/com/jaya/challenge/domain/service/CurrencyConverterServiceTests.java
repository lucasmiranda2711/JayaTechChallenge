package com.jaya.challenge.domain.service;


import com.jaya.challenge.rest.CurrencyExchangeClient;
import com.jaya.challenge.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterServiceTests {


    @Test
    public void shouldReturnTheCurrencyRequested() {
        var mockedRequestClient = Mockito.mock(CurrencyExchangeClient.class);
        var restConfiguration = TestUtils.createRestConfiguration();
        var mockedCurrencyRequest = TestUtils.createCurrencyRequestDto();
        var mockedApiResponse = TestUtils.createMonoMockedApiResponseDto(mockedCurrencyRequest);

        Mockito.when(mockedRequestClient
                .getCurrencies(Mockito.any()))
                .thenReturn(mockedApiResponse);

        CurrencyConverterService currencyConverterService =
                new CurrencyConverterService(mockedRequestClient, restConfiguration);

        var response = currencyConverterService
                .convertCurrency(mockedCurrencyRequest.getCurrencyOrigin().name(),
                        mockedCurrencyRequest.getCurrencyDestination().name());

        assertEquals(mockedApiResponse, response);

    }

}
