package com.jaya.challenge.dto;

import com.jaya.challenge.enums.CurrencyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CurrencyResponseDto extends CurrencyBaseDto{

    @Builder
    public CurrencyResponseDto(int userId, CurrencyType currencyOrigin, BigDecimal valueOrigin, CurrencyType currencyDestination, BigDecimal conversionTax, String transactionId, BigDecimal valueDestination, LocalDateTime dateOfRequest) {
        super(userId,  currencyOrigin,  valueOrigin,  currencyDestination);
        this.transactionId = transactionId;
        this.valueDestination = valueDestination;
        this.dateOfRequest = dateOfRequest;
        this.conversionTax = conversionTax;
    }

    private String transactionId;
    private BigDecimal valueDestination;
    private LocalDateTime dateOfRequest;
    private BigDecimal conversionTax;

}
