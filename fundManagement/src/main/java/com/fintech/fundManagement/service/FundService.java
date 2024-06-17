package com.fintech.fundManagement.service;

import com.fintech.fundManagement.model.Fund;
import com.fintech.fundManagement.model.StockDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

public interface FundService {
    public Flux<Fund> getAllFunds();

    public Mono<Fund> getFundById(String id);

    public Mono<Fund> createFund(Fund fund);

    public Mono<Fund> updateFund(String id, Fund fund);

    public Mono<Void> deleteFund(String id);

    public Mono<Fund> addMoneyToFund(String fundId, BigDecimal amount);

    public Mono<Fund> updateStockPrices(String fundId, Map<String, StockDetails> stockDetails);

    public Mono<BigDecimal> getCurrentNav(String fundId);

}

