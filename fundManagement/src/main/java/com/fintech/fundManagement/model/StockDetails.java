package com.fintech.fundManagement.model;

import java.math.BigDecimal;

import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDetails {
    private BigDecimal value; // Value of the stock
    private BigDecimal quantity; // Quantity of the stock
}
