package com.jaya.challenge.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Builder
public class CurrencyApiResponseDto {

    private boolean success;
    private int timestamp;
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;

}
