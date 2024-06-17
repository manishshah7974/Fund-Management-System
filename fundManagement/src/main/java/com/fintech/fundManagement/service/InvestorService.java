package com.fintech.fundManagement.service;

import com.fintech.fundManagement.model.Investor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface InvestorService {
    public Mono<Investor> purchaseFundShares(String investorId, String fundId, BigDecimal amount);
    public Flux<Investor> getAllInvestors();
    public Mono<Investor> getInvestorById(String id);
    public Mono<Investor> createInvestor(Investor investor);

    public Mono<Investor> updateInvestor(String id, Investor investor);

    public Mono<Void> deleteInvestor(String id);

    }