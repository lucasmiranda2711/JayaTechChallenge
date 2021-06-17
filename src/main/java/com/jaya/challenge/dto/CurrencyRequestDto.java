package com.jaya.challenge.dto;

import com.jaya.challenge.enums.CurrencyType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto extends CurrencyBaseDto{

    public CurrencyRequestDto(int userId, @NotNull(message = "The field currencyOrigin cannot be null.") CurrencyType currencyOrigin, @NotNull(message = "The field valueOrigin cannot be null.") BigDecimal valueOrigin, @NotNull(message = "The field currencyDestination cannot be null.") CurrencyType currencyDestination) {
        super(userId, currencyOrigin, valueOrigin, currencyDestination);
    }
}
