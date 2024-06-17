package com.fintech.fundManagement.service;

import com.fintech.fundManagement.model.Fund;
import com.fintech.fundManagement.model.StockDetails;
import com.fintech.fundManagement.repository.FundRepository;
import com.fintech.fundManagement.utils.Logger.LoggerSingleton;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundRepository fundRepository;
    private final Logger logger = LoggerSingleton.getInstance().getLogger();

    public Flux<Fund> getAllFunds() {
        return fundRepository.findAll().onBackpressureBuffer(1000, BufferOverflowStrategy.DROP_OLDEST).log();
        // Buffer with a size of 1000 items
    }

    public Mono<Fund> getFundById(String id) {
        return fundRepository.findById(id);
    }

    public Mono<Fund> createFund(Fund fund) {
        return fundRepository.save(fund);
    }

    public Mono<Fund> updateFund(String id, Fund fund) {
        return fundRepository.findById(id).flatMap(existingFund -> {
            existingFund.setName(fund.getName());
            existingFund.setNetAssetValue(fund.getNetAssetValue());
            existingFund.setTotalInvestment(fund.getTotalInvestment());
            existingFund.setStocks(fund.getStocks());
            return fundRepository.save(existingFund);
        });
    }

    public Mono<Void> deleteFund(String id) {
        return fundRepository.deleteById(id);
    }

    public Mono<Fund> addMoneyToFund(String fundId, BigDecimal amount) {
        return fundRepository.findById(fundId).flatMap(fund -> {
            fund.setTotalInvestment(fund.getTotalInvestment().add(amount));
            fund.setNetAssetValue(fund.getNetAssetValue().add(amount));
            return fundRepository.save(fund);
        });
    }

    public Mono<Fund> updateStockPrices(String fundId, Map<String, StockDetails> stockDetails) {
        return fundRepository.findById(fundId).flatMap(fund -> {
            fund.setStocks(stockDetails);
            // Calculate the new net asset value based on updated stock details
            BigDecimal newNetAssetValue = stockDetails.values().stream().map(sd -> sd.getValue().multiply(sd.getQuantity())).reduce(BigDecimal.ZERO, BigDecimal::add);
            fund.setNetAssetValue(newNetAssetValue);
            return fundRepository.save(fund);
        });
    }

    public Mono<BigDecimal> getCurrentNav(String fundId) {
        return fundRepository.findById(fundId).map(Fund::getNetAssetValue);
    }
}
