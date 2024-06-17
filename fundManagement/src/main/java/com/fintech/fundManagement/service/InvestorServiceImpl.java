package com.fintech.fundManagement.service;

import com.fintech.fundManagement.model.Fund;
import com.fintech.fundManagement.model.Investment;
import com.fintech.fundManagement.model.Investor;
import com.fintech.fundManagement.repository.FundRepository;
import com.fintech.fundManagement.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class InvestorServiceImpl implements InvestorService {
    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private FundRepository fundRepository;

    public Mono<Investor> purchaseFundShares(String investorId, String fundId, BigDecimal amount) {
        return investorRepository.findById(investorId).zipWith(fundRepository.findById(fundId)).flatMap(tuple -> {
            Investor investor = tuple.getT1();
            Fund fund = tuple.getT2();

            Investment investment = investor.getInvestments().getOrDefault(fundId, new Investment());
            investment.setAmount(investment.getAmount() != null ? investment.getAmount().add(amount) : amount);
            investment.setNavAtPurchase(fund.getNetAssetValue());
            investment.setCurrentNav(fund.getNetAssetValue());
            investor.getInvestments().put(fundId, investment);

            fund.setNetAssetValue(fund.getNetAssetValue().add(amount));
            fund.setTotalInvestment(fund.getTotalInvestment() != null ? fund.getTotalInvestment().add(amount) : amount);

            return fundRepository.save(fund).then(investorRepository.save(investor));
        });
    }

    public Flux<Investor> getAllInvestors() {
        return investorRepository.findAll().onBackpressureBuffer(1000, BufferOverflowStrategy.ERROR);
    }

    public Mono<Investor> getInvestorById(String id) {
        return investorRepository.findById(id);
    }

    public Mono<Investor> createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    public Mono<Investor> updateInvestor(String id, Investor investor) {
        return investorRepository.findById(id).flatMap(existingInvestor -> {
            existingInvestor.setName(investor.getName());
            existingInvestor.setInvestments(investor.getInvestments());
            return investorRepository.save(existingInvestor);
        });
    }

    public Mono<Void> deleteInvestor(String id) {
        return investorRepository.deleteById(id);
    }
}