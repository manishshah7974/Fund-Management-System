package com.fintech.fundManagement.controller;

import com.fintech.fundManagement.model.Investment;
import com.fintech.fundManagement.model.Investor;
import com.fintech.fundManagement.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @PostMapping("/{investorId}/purchase/{fundId}")
    public Mono<Investor> purchaseFundShares(@PathVariable String investorId, @PathVariable String fundId, @RequestBody BigDecimal amount) {
        return investorService.purchaseFundShares(investorId, fundId, amount);
    }

    @GetMapping
    public Flux<Investor> getAllInvestors() {
        return investorService.getAllInvestors().onBackpressureBuffer(1000, BufferOverflowStrategy.DROP_LATEST);
    }

    @GetMapping("/{id}")
    public Mono<Investor> getInvestorById(@PathVariable String id) {
        return investorService.getInvestorById(id);
    }

    @PostMapping
    public Mono<Investor> createInvestor(@RequestBody Investor investor) {
        return investorService.createInvestor(investor);
    }

    @PutMapping("/{id}")
    public Mono<Investor> updateInvestor(@PathVariable String id, @RequestBody Investor investor) {
        return investorService.updateInvestor(id, investor);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteInvestor(@PathVariable String id) {
        return investorService.deleteInvestor(id);
    }

    @GetMapping("/{investorId}/investments")
    public Mono<Map<String, Investment>> getInvestments(@PathVariable String investorId) {
        return investorService.getInvestorById(investorId)
                .map(Investor::getInvestments);
    }

    @GetMapping("/{investorId}/investments/{fundId}")
    public Mono<Investment> getInvestment(@PathVariable String investorId, @PathVariable String fundId) {
        return investorService.getInvestorById(investorId)
                .map(investor -> investor.getInvestments().get(fundId));
    }

}
