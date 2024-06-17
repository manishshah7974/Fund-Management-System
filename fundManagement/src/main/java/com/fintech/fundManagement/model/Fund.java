package com.fintech.fundManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;

import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Fund {
    @Id
    private String id; // Unique identifier for the fund
    private String name; // Name of the fund
    private BigDecimal netAssetValue; // Net Asset Value of the fund
    private BigDecimal totalInvestment; // Total amount of money invested in the fund
    private Map<String, StockDetails> stocks; // Map of stock symbols to their details (value and quantity)

}
