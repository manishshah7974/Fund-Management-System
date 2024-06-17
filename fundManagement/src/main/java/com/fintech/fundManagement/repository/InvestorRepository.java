package com.fintech.fundManagement.repository;

import com.fintech.fundManagement.model.Investor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends ReactiveMongoRepository<Investor, String> {
}
