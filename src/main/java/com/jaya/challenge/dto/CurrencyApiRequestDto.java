package com.jaya.challenge.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrencyApiRequestDto {

    private String access_key;
    private String symbols;
}
