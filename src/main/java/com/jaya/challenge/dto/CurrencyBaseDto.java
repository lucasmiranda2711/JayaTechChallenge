package com.jaya.challenge.dto;

import com.jaya.challenge.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
class CurrencyBaseDto {

    private int userId;
    @NotNull(message = "The field currencyOrigin cannot be null.") private CurrencyType currencyOrigin;
    @NotNull(message = "The field valueOrigin cannot be null.") private BigDecimal valueOrigin;
    @NotNull(message = "The field currencyDestination cannot be null.") private CurrencyType currencyDestination;

}
