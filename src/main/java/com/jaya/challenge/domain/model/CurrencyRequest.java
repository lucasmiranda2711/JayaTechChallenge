package com.jaya.challenge.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class CurrencyRequest {

    @Id
    private String transactionId;
    private int userId;
    private String currencyOrigin;
    private BigDecimal valueOrigin;
    private String currencyDestination;
    private BigDecimal valueDestination;
    private BigDecimal conversionTax;
    private LocalDateTime dateOfRequest;
}
