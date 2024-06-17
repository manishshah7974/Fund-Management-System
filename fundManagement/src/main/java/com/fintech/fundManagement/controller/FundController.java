package com.fintech.fundManagement.controller;

import com.fintech.fundManagement.model.Fund;
import com.fintech.fundManagement.model.StockDetails;
import com.fintech.fundManagement.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/funds")
public class FundController {

    @Autowired
    private FundService fundService;


    @GetMapping
    public Flux<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }
    // Can also send data as Streams
    @GetMapping(value = "allFundsSocket", produces = "text/event-stream")
    public Flux<Fund> getAllFundssSocket() {
        return fundService.getAllFunds().delayElements(Duration.ofSeconds(20));
    }

    @GetMapping("/{id}")
    public Mono<Fund> getFundById(@PathVariable String id) {
        return fundService.getFundById(id);
    }

    @GetMapping("/{id}/nav")
    public Mono<BigDecimal> getCurrentNav(@PathVariable String id) {
        return fundService.getCurrentNav(id);
    }

    @PostMapping
    public Mono<Fund> createFund(@RequestBody Fund fund) {
        return fundService.createFund(fund);
    }

    @PutMapping("/{id}")
    public Mono<Fund> updateFund(@PathVariable String id, @RequestBody Fund fund) {
        return fundService.updateFund(id, fund);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFund(@PathVariable String id) {
        return fundService.deleteFund(id);
    }

    @PostMapping("/{id}/add-money")
    public Mono<Fund> addMoneyToFund(@PathVariable String id, @RequestBody BigDecimal amount) {
        return fundService.addMoneyToFund(id, amount);
    }

    @PostMapping("/{id}/update-stocks")
    public Mono<Fund> updateStockPrices(@PathVariable String id, @RequestBody Map<String, StockDetails> stockDetails) {
        return fundService.updateStockPrices(id, stockDetails);
    }
}
