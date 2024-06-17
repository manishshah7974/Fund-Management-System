package com.fintech.fundManagement.model;

import java.math.BigDecimal;
import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    private BigDecimal amount;
    private BigDecimal navAtPurchase;
    private BigDecimal currentNav;
}
