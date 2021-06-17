package com.jaya.challenge.rest;

import com.jaya.challenge.dto.CurrencyApiRequestDto;
import com.jaya.challenge.dto.CurrencyApiResponseDto;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(url = "http://api.exchangeratesapi.io/v1/latest", name = "exchange")
public interface CurrencyExchangeClient {

    @RequestMapping(method = RequestMethod.GET)
    Mono<CurrencyApiResponseDto> getCurrencies(@SpringQueryMap CurrencyApiRequestDto currencyApiRequestDto);

}
