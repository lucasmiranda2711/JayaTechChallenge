package com.jaya.challenge.controller;

import com.jaya.challenge.application.CurrencyConverterApplicationService;
import com.jaya.challenge.dto.CurrencyRequestDto;
import com.jaya.challenge.dto.CurrencyResponseDto;
import com.jaya.challenge.enums.CurrencyType;
import com.jaya.challenge.exception.NotImplementedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("currency")
public class CurrencyConverterController {

    private final CurrencyConverterApplicationService currencyConverterApplicationService;

    public CurrencyConverterController(CurrencyConverterApplicationService currencyConverterApplicationService) {
        this.currencyConverterApplicationService = currencyConverterApplicationService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/convert")
    public Mono<CurrencyResponseDto> convertCurrency(@RequestBody @Valid CurrencyRequestDto currencyRequestDto){
        if(currencyRequestDto.getCurrencyOrigin() != CurrencyType.EUR){
            throw new NotImplementedException("The currency informed is not yet supported");
        }
        return currencyConverterApplicationService.convertCurrency(currencyRequestDto);
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public Flux<CurrencyResponseDto> findAll(){
        return currencyConverterApplicationService.findAll();
    }
}
